package com.tinx.java.common.mybatis.config;

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.tinx.java.common.mybatis.ext.injector.SqlInjector;
import com.tinx.java.common.mybatis.handler.DataMetaObjectHandler;
import com.tinx.java.common.utils.ListUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author tinx
 * @date 2018-10-25 18:15
 */
public abstract class AbstractMybatisConfig extends MapperScannerConfigurer {

    private static Logger logger = LoggerFactory.getLogger(AbstractMybatisConfig.class);

    private static final String DEFAULT_MODULE_PREFIX = "mypro";

    protected ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        super.setApplicationContext(applicationContext);
        this.applicationContext = applicationContext;
    }

    public abstract String getModelName();

    /**
     * 获取mybatis目录扫描配置
     * @return
     */
    public MybatisScannerConfig getMybatisScanConfig() {
        Package aPackage = this.getClass().getPackage();
        String[] split = aPackage.getName().split("\\.");
        if (split.length > 3) {
            split = ArrayUtils.subarray(split, 0, 3);
        }
        String packageName = ListUtil.join(Arrays.asList(split), ".");

        String modelName = getModelName();
        String basePackage = String.format(packageName +".%s.mapper", modelName);
        String typeAliasesPackage = String.format(packageName + ".%s.model", modelName);

        MybatisScannerConfig config = new MybatisScannerConfig();
        config.setBasePackage(basePackage);
        config.setTypeAliasesPackage(typeAliasesPackage);
        return config;
    }

    public static class MybatisScannerConfig {
        private String basePackage;
        private String mapperLocations;
        private String typeAliasesPackage;

        public String getBasePackage() {
            return basePackage;
        }

        public void setBasePackage(String basePackage) {
            this.basePackage = basePackage;
        }

        public String getMapperLocations() {
            return mapperLocations;
        }

        public void setMapperLocations(String mapperLocations) {
            this.mapperLocations = mapperLocations;
        }

        public String getTypeAliasesPackage() {
            return typeAliasesPackage;
        }

        public void setTypeAliasesPackage(String typeAliasesPackage) {
            this.typeAliasesPackage = typeAliasesPackage;
        }
    }

    protected DataSource makeDefaultDataSource() {
        String prefix = getModelName();
        if(StringUtils.isBlank(prefix)) {
            prefix = DEFAULT_MODULE_PREFIX;
        }

        if (prefix.indexOf(".") > 0) {
            throw new RuntimeException("模块名称非法, 不能含有'.'");
        }


        String url = applicationContext.getEnvironment().getProperty(prefix + ".datasource.url");
        if (StringUtils.isBlank(url)) {
            url = applicationContext.getEnvironment().getProperty(DEFAULT_MODULE_PREFIX + ".datasource.url");
        }
        String username = applicationContext.getEnvironment().getProperty(prefix + ".datasource.username");
        if (StringUtils.isBlank(username)) {
            username = applicationContext.getEnvironment().getProperty(DEFAULT_MODULE_PREFIX + ".datasource.username");
        }
        String password = applicationContext.getEnvironment().getProperty(prefix + ".datasource.password");
        if (StringUtils.isBlank(password)) {
            password = applicationContext.getEnvironment().getProperty(DEFAULT_MODULE_PREFIX + ".datasource.password");
        }
        return makeDataSource(url, username, password);
    }

    protected DataSource makeDataSource(String url, String username, String password) {
        if (url.indexOf("?") < 0) {
            url = url + "?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&autoReconnect=true&failOverReadOnly=false";
        }
        logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXxx:url={},username={},password={}",url,username,password);

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)applicationContext.getAutowireCapableBeanFactory();

        // data source
        String datasourceBeanName = this.getClass().getName() + "_" + getModelName() + "_datasource";
        DataSource dataSource = getDataSource();
        beanFactory.registerSingleton(datasourceBeanName, dataSource);

        MybatisScannerConfig config = getMybatisScanConfig();
        SqlSessionFactory sqlSessionFactory = makeSqlSessionFactory(dataSource, globalConfiguration(), paginationInterceptor(),
                config.getMapperLocations(), config.getTypeAliasesPackage());

        String factoryBeanName = this.getClass().getName() + "_" + getModelName() + "_SqlSessionFactory";
        beanFactory.registerSingleton(factoryBeanName, sqlSessionFactory);

        setBasePackage(config.getBasePackage());
        setSqlSessionFactoryBeanName(factoryBeanName);
        logger.info("afterPropertiesSet:");
        super.afterPropertiesSet();
    }

    protected DataSource getDataSource() throws SQLException {
        return makeDefaultDataSource();
    };

    private SqlSessionFactory makeSqlSessionFactory(DataSource dataSource,
                                                    GlobalConfiguration globalConfiguration,
                                                    PaginationInterceptor paginationInterceptor,
                                                    String mapperLocations,
                                                    String typeAliasesPackage) throws Exception {

        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);
        if (StringUtils.isNotBlank(mapperLocations)) {
            sqlSessionFactory.setMapperLocations(applicationContext.getResources(mapperLocations));
        }
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{
                paginationInterceptor,
                // new PerformanceInterceptor(),
                new OptimisticLockerInterceptor()
        });
        sqlSessionFactory.setGlobalConfig(globalConfiguration);
        return sqlSessionFactory.getObject();

    }

    GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration(new SqlInjector());
        conf.setLogicDeleteValue("0");
        conf.setLogicNotDeleteValue("1");
        conf.setIdType(PrimaryKeyType()); // #主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
        conf.setFieldStrategy(filedStrategy()); // #字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
        conf.setRefresh(true); // #刷新mapper 调试神器
        conf.setDbColumnUnderline(true); // #驼峰下划线转换
        conf.setMetaObjectHandler(new DataMetaObjectHandler()); // #自定义填充策略接口实现
        return conf;
    }

    PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLocalPage(true);
        paginationInterceptor.setOverflowCurrent(true);
        return paginationInterceptor;
    }

    /**
     * 主键类型， 主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
     * @return
     */
    protected int PrimaryKeyType() {
        return 0;
    }

    /**
     * 字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
     * @return
     */
    protected int filedStrategy() {
        return 1;
    }

}
