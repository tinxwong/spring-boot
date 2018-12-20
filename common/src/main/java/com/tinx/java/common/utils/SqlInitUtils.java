package com.tinx.java.common.utils;

import com.tinx.java.common.exception.IllegalException;
import com.tinx.java.common.mybatis.config.AbstractMybatisConfig;
import com.tinx.java.common.response.status.interfaces.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tinx
 * @date 2018-11-28 9:35
 */
public class SqlInitUtils {

    private static Logger logger = LoggerFactory.getLogger(SqlInitUtils.class);

    private static String SCHEMA_TEMPLATE = "create schema %s default character set utf8mb4";//"create schema %s default character set utf8mb4 collate utf8mb4_unicode_ci";

    private static String MIGRATION_TABLE = "CREATE TABLE `migration`(`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',`file_name` varchar(255) DEFAULT NULL COMMENT '文件名称', `file_mdkey` varchar(255) DEFAULT NULL COMMENT '文件key',`created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间', PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    private static String MIGRATION_INSERT = "INSERT INTO `migration` (file_name,file_mdkey) VALUES ('%s','%s');";

    private static String MIGRATION_CHECK = "select * from `migration` where file_mdkey='%s'";

    private static String MIGRATION_SELECT = "select count(id) from `migration` where 1=1";

    private static String TABLE_SYS = "sys";

    private static String TABLE_TEST = "test";
    /**
     * 初始化数据库环境入口，包括建库、建表
     * @param url
     * @param user
     * @param pwd
     * @param moduleName
     */
    public static void initDatabase(String url,String user,String pwd,String moduleName){

        Connection conn = null;
        int count = 0;
        try {
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            if(conn==null){
                logger.info("数据库{}不存在，开始创建!",getDatabaseName(url));
                conn = createDatabase(url,user,pwd);
                if(conn==null){
                    logger.error("初始化数据库错误！");
                    return ;
                }
            }
        }
//        if(!checkMigrationTable(conn)){//不存在migration表,需要创建
//            if(createMigrationTable(conn)){
//                throw new RuntimeException("创建migration表失败！");
//            }
//        }else{
//            executeModuleSql(conn,moduleName);
//        }
        if(!checkMigrationTable(conn)&&!createMigrationTable(conn)){//创建执行sql文件记录表
            throw new RuntimeException("创建migration表失败！");
        }
        executeModuleSql(conn,moduleName);
        try {
            if(conn!=null){
                conn.close();
                conn = null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("关闭数据库资源失败！");
        }
    }

    /**
     * 创建数据库
     * @param url
     * @param user
     * @param pwd
     * @return
     */
    public static Connection createDatabase(String url,String user,String pwd){
        String databaseName = getDatabaseName(url);
        String sysUrl = url.replace(databaseName,TABLE_SYS);
        Connection conn = null;
        Statement stat = null;
        try {
            conn = DriverManager.getConnection(sysUrl, user, pwd);
            if(conn==null){
                String testUrl = url.replace(databaseName,TABLE_TEST);//5.6以前版本的mysql,有test表
                conn = DriverManager.getConnection(testUrl, user, pwd);
            }

            String createSchemaSql = String.format(SCHEMA_TEMPLATE,databaseName);
            logger.info("创建数据库语句={}",createSchemaSql);
            stat = conn.createStatement();
            if(stat.executeUpdate(createSchemaSql)>0){
                conn = DriverManager.getConnection(url, user, pwd);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }finally {
            try {
                if(stat!=null){
                    stat.close();
                    stat = null;
                }
            } catch (SQLException e) {
                stat = null;
            }
        }
        return conn;
    }

    /**
     * 从提供的链接中提取数据库名
     * @param url
     * @return
     */
    public static String getDatabaseName(String url){
        String databaseName = "";
        String[] arrUrl = url.split("\\?");
        String[] arrStr = arrUrl[0].split("/");
        databaseName = arrStr[arrStr.length-1];
        return databaseName;
    }

    /**
     * 创建migration表，用来记录执行sql文件
     * @param conn
     * @return
     */
    public static boolean createMigrationTable(Connection conn){
        logger.info("开始创建migration表");
        Statement st = null;
        try {
            st = conn.createStatement();
            int count = st.executeUpdate(MIGRATION_TABLE);
            logger.info("创建migration表是否成功={}",count);
            return count==0?true:false;
        } catch (SQLException e) {
            return false;
        }finally {
            try {
                if(st!=null){
                    st.close();
                    st = null;
                }
            } catch (SQLException e) {
                logger.error("释放数据库资源异常:"+e.getMessage());
                return false;
            }
        }
    }

    /**
     * 检查migration表是否存在
     * @param conn
     * @return 存在 返回true,不存在 返回false
     */
    public static boolean checkMigrationTable(Connection conn){
        logger.info("检查migration表是否存在");
        PreparedStatement pStat = null;
        ResultSet rs = null;
        boolean isExist =false;
        try {
            pStat = conn.prepareStatement(MIGRATION_SELECT);
            rs = pStat.executeQuery();
            if(rs.next()){
                isExist = true;
                logger.info("migration表存在，不需要创建!");
            }
        } catch (SQLException e) {
            isExist = false;
        }finally {
            try {
                if(rs!=null){
                    rs.close();
                    rs = null;
                }
                if(pStat!=null){
                    pStat.close();
                    pStat = null;
                }
            } catch (SQLException e) {
                rs = null;
                pStat = null;
                logger.error("释放数据库资源异常:"+e.getMessage());
            }

        }
        return isExist;
    }

    /**
     * 执行模块的sql文件里边的SQL语句
     * @param moduleName
     */
    public static void executeModuleSql(Connection conn,String moduleName){
        if(conn==null){
            return ;
        }
        List<String> sqlFileList = loadSqlFiles(moduleName);
        logger.info("模块={},sql文件={}:",moduleName,sqlFileList);
        Statement stmt = null;
        if(!CollectionUtils.isEmpty(sqlFileList)){
            for(String sqlFilePath:sqlFileList){
                if(checkByMigration(conn,sqlFilePath)){//检查该文件是否已经执行过
                    continue;
                }
                List<String> sqlList = loadFileContent(sqlFilePath);
                for(String sql:sqlList){
                    if(sql.startsWith("INSERT")){
                        System.out.println("insert 语句:"+sql);
                    }
                    try{
                        stmt = conn.createStatement();
                        int count = stmt.executeUpdate(sql);
                        logger.info("执行sql语句={},count={}",sql,count);
                    } catch (SQLException e) {
                        logger.error("执行sql语句失败!"+sql);
                        logger.error(e.getMessage());
                    }
                }

                recordMigration(conn,sqlFilePath);//记录执行的sql文件
//                List<String> sqlList = loadFileContent(sqlFilePath);
//                if(!CollectionUtils.isEmpty(sqlList)){
//                    try{
//                        stmt = conn.createStatement();
//                        for (String sql : sqlList) {
//                            sql = sql.trim();
//                            if(sql!=null&&!sql.equals(""))
//                                stmt.addBatch(sql);
//                        }
//                        int[] rows = stmt.executeBatch();
//                        recordMigration(conn,sqlFilePath);//记录执行的sql文件
//                        logger.info("一共执行sql语句:Row count:" + Arrays.toString(rows));
//                    } catch (SQLException e) {
//                        logger.error("批量执行sql语句失败!");
//                        logger.error(e.getMessage());
//                    }
//;                }
            }
            try {
                if(stmt!=null){
                    stmt.close();
                    stmt = null;
                }
            } catch (SQLException e) {
                logger.error("关闭数据库资源失败:"+e.getMessage());
            }
        }

    }


    /**
     * 遍历模块的sql文件
     * @param moduleName
     */
    public static List<String> loadSqlFiles(String moduleName){
        List<String> list = new ArrayList<>();
        ResourcePatternResolver loader = new PathMatchingResourcePatternResolver();
        String resourceName = String.format("classpath:/%s/*.sql",moduleName);
        try{
            Resource[] resources = loader.getResources(resourceName);
            if(resources!=null&&resources.length>0){
                for(int i=0;i< resources.length;i++) {
                    StringBuffer stringBuffer  = new StringBuffer();
                    String file = resources[i].getURL().getFile();
                    String myPath = "";
                    if(file.contains("!/")){
                        String[] arrPath = file.split("!/");
                        myPath = arrPath[arrPath.length-1];
                    }else{
                        String[] arrPath = file.split("classes/");
                        myPath = arrPath[arrPath.length-1];
                    }

                    list.add(myPath);
                }
            }
        }catch(FileNotFoundException e){
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return list;

    }

    /**
     * 分析SQL文件的内容,提取出所有的sql语句
     * @param path
     * @return
     */
    public static String loadWholeContent(String path){
        StringBuffer stringBuffer = new StringBuffer();
        InputStream stream = getContextClassLoader().getResourceAsStream(path);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                stringBuffer.append(line);
            }
        } catch (IOException e){
            logger.error("读取文件出错："+e.getMessage());
        }
        logger.info("{}的sql语句数量{}:",path,stringBuffer.toString());
        return stringBuffer.toString();
    }

    /**
     * 分析SQL文件的内容,提取出所有的sql语句
     * @param path
     * @return
     */
    public static List<String> loadFileContent(String path){
        List<String> sqlList = new ArrayList<>();

        StringBuffer stringBuffer = new StringBuffer();
        InputStream stream = getContextClassLoader().getResourceAsStream(path);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String line;
            List<String> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if(list.size()>0){
                    if(line.endsWith("*/")||line.endsWith("*/;")){
                        list.remove(list.size()-1);

                    }
                    continue;
                }
                if(line.startsWith("#")||line.startsWith("-- ")||line.startsWith("//")){
                    continue;
                }
                if(line.startsWith("/*")){
                    if(line.endsWith("*/")||line.endsWith("*/;")){
                        continue;
                    }else{
                        list.add("/*");
                        continue;
                    }
                }
                stringBuffer.append(line);

                if(line.endsWith(";")){
                    sqlList.add(stringBuffer.toString());
                    stringBuffer = new StringBuffer();
                }
            }

        } catch (IOException e){
            logger.error("读取文件出错："+e.getMessage());
        }
        logger.info("{}的sql语句数量{}:",path,sqlList.size());
        stringBuffer.toString();

        return sqlList;
    }

    /**
     * 记录执行过的sql文件
     * @param conn
     * @param fileName
     */
    public static void recordMigration(Connection conn,String fileName){
        String fileMdKey = MD5(fileName);
        String insertSql = String.format(MIGRATION_INSERT,fileName,fileMdKey);
        logger.info("记录执行的sql文件语句={}",insertSql);
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(insertSql);
            stat.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }finally {
            try {
                if(stat!=null){
                    stat.close();
                    stat = null;
                }

            } catch (SQLException e) {
                logger.error("释放数据库资源失败:"+e.getMessage());
            }

        }

    }

    /**
     * 执行前先检查改sql文件是否被执行
     * @param conn
     * @param fileName
     * @return
     */
    public static boolean checkByMigration(Connection conn,String fileName){
        String mdkey = MD5(fileName);
        String sql = String.format(MIGRATION_CHECK,mdkey);
        logger.info("检查文件={}是否存在的sql语句={}",fileName,sql);
        PreparedStatement pStat = null;
        ResultSet rs = null;
        boolean isExist =false;
        try {
            pStat = conn.prepareStatement(sql);
            rs = pStat.executeQuery();
            if(rs.next()){
                isExist = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            try {
                if(rs!=null){
                    rs.close();
                    rs = null;
                }
               if(pStat!=null){
                   pStat.close();
                   pStat = null;
               }
            } catch (SQLException e) {
                rs = null;
                pStat = null;
                logger.error("释放数据库资源异常:"+e.getMessage());
            }

        }
        return isExist;
    }

    private static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }


    public static String MD5(String value) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(value.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException e) {
            logger.error("", e);
        }
        catch (UnsupportedEncodingException e) {
            logger.error("", e);
        }

        if (messageDigest == null) {
            return null;
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            }
            else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }

        return md5StrBuff.toString();
    }



//    public static void loadSqlFiles(String moduleName){
//        ResourcePatternResolver loader = new PathMatchingResourcePatternResolver();
//        String resourceName = String.format("classpath:/%s/*.sql",moduleName);
//        PreparedStatement st = null;
//        try{
//            Resource[] resources = loader.getResources(resourceName);
//            if(resources!=null&&resources.length>0){
//                for(int i=0;i< resources.length;i++) {
//                    StringBuffer stringBuffer  = new StringBuffer();
//                    String file = resources[i].getURL().getFile();
//                    List<String> list = loadFileContent(file);
//                }
//            }
//        }catch(FileNotFoundException e){
//            logger.error(e.getMessage());
//        } catch (IOException e) {
//            logger.error(e.getMessage());
//        }
//
//    }
//
//
//    public static List<String> loadFile(String path){
//        File file = new File(path);
//        List<String> sqlList = new ArrayList<>();
//        StringBuffer stringBuffer = new StringBuffer();
//        try {
//            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
//            BufferedReader bReader = new BufferedReader(isr);
//            String line;
//            List<String> list = new ArrayList<>();
//            int i = 0;
//            while ((line = bReader.readLine()) != null) {
//                line = line.trim();
//                if(list.size()>0){
//                    if(line.endsWith("*/")||line.endsWith("*/;")){
//                        list.remove(list.size()-1);
//
//                    }
//                    continue;
//                }
//                if(line.startsWith("#")||line.startsWith("-- ")||line.startsWith("//")){
//                    continue;
//                }
//                if(line.startsWith("/*")){
//                    if(line.endsWith("*/")||line.endsWith("*/;")){
//                        continue;
//                    }else{
//                        list.add("/*");
//                        continue;
//                    }
//                }
//                stringBuffer.append(line);
//
//                if(line.endsWith(";")){
//                    System.out.println("这个是第"+(i++)+"sql语句："+stringBuffer.toString());
//                    sqlList.add(stringBuffer.toString());
//                    stringBuffer = new StringBuffer();
//                }
//            }
//
//        } catch (MalformedURLException e) {
//            //TODO
//        }catch (IOException e){
//            //TODO
//        }
//
//        stringBuffer.toString();
//
//        return sqlList;
//    }




}
