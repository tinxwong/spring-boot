package com.tinx.java.common.utils;
import java.io.*;
	import java.util.ArrayList;
	import java.util.Enumeration;
	import java.util.List;
	import java.util.Vector;
import java.util.regex.Pattern;

/**
 * @author tinx
 * @date 2018-12-2 16:58
 */
public class SqlHelper {

    public static void test() {
        String sqlStr = "select id -- 完成 \n " + " ,CODE_ID,CODE_NAME from code_table";
        Pattern p = Pattern.compile("(?ms)('(?:''|[^'])*')|--.*?$|/\\*.*?\\*/|#.*?$|");
        String result = p.matcher(sqlStr).replaceAll("$1");
        System.err.println(result);
    }
public static void main(String[]args){
        List list = new ArrayList();
        list.add("/*");
    list.add("/*");
    list.add("/*");
    System.out.println(list);
//
//    String filePath = "D:\\app\\programme\\IdeaProjects\\spring-boot\\apps\\survey\\src\\main\\resources\\survey\\create.sql";
//        String sql=GetText(filePath);
//        String[]arr=getsql(sql);
//        for(int i=0;i<arr.length;i++)
//        System.out.println("第"+i+"句:"+arr[i]);

        }
public static String GetText(String path){
        File file=new File(path);
        if(!file.exists()||file.isDirectory())
        return null;
        StringBuffer sb=new StringBuffer();
        try
        {
        FileInputStream fis=new FileInputStream(path);
        InputStreamReader isr=new InputStreamReader(fis,"UTF-8");
        BufferedReader br=new BufferedReader(isr);
        String temp=null;
        temp=br.readLine();
        while(temp!=null){
        sb.append(temp+"\r\n");
        temp=br.readLine();
        }
        }catch(Exception e){
        e.printStackTrace();
        }
        return sb.toString();
        }

public static String[]getsql(String sql)
        {
        String s=sql;
        s=s.replace("\r\n","\r");
        s=s.replace("\r","\n");
        String[]ret=new String[1000];
        String[]sqlarray=s.split(";\n");
        sqlarray=filter(sqlarray);
        int num=0;
        for(String item:sqlarray)
        {
        String ret_item="";
        String[]querys=item.trim().split("\n");
        querys=filter(querys);//去空
        for(String query:querys)
        {
        String str1=query.substring(0,1);
        String str2=query.substring(0,2);
        if(str1.equals("#")||str2.equals("--")||str2.equals("/*")||str2.equals("//"))//去除注释的关键步奏
        {
        continue;
        }
        ret_item+=query;
        }
        ret[num]=ret_item;
        num++;
        }
        return filter(ret);
        }
/// <summary>
/// 去除空值数组
/// </summary>
/// <param name="ss">数组</param>
/// <returns></returns>
public static String[]filter(String[]ss)
        {
        List<String> strs=new ArrayList<String>();
        for(String s:ss){
        if(s!=null&&!s.equals(""))
        strs.add(s);
        }

        String[]result=new String[strs.size()];
        for(int i=0;i<strs.size();i++)
        {
        result[i]=strs.get(i).toString();
        }
        return result;
        }

//删除注释
public void deletezs(String fileStr)
        {
        try{
        Vector<String> vec=new Vector<String>();
        String str="",tm="",mm="";
        BufferedReader br=new BufferedReader(new FileReader(fileStr));
        boolean bol=false;
        while(null!=(str=br.readLine()))
        {
        if((str.indexOf("/*")>=0)&&((bol==false)))
        {
        if(str.indexOf("*/")>0)
        {
        bol=false;
        vec.addElement(str.substring(0,str.indexOf("/*"))+str.substring(str.indexOf("*/")+2,str.length()));
        }
        else
        {
        bol=true;
        mm=str.substring(0,str.indexOf("/*"));
        if(!(mm.trim().equals("")))
        vec.addElement(mm);
        }
        }
        else if(bol==true)
        {
        if(str.indexOf("*/")>=0)
        {
        bol=false;
        mm=str.substring(str.indexOf("*/")+2,str.length());
        if(!mm.trim().equals(""))
        vec.addElement(mm);
        }
        }
        else if(str.indexOf("//")>=0)
        {
        tm=str.substring(0,str.indexOf("//"));
        if(!tm.trim().equals(""))
        vec.addElement(tm);
        }
        else
        {
        vec.addElement(str);
        }
        }
        br.close();
        File fName=new File(fileStr);
        FileWriter in=new FileWriter(fName);
        String ssss="";
        Enumeration<String> ew=vec.elements();

        while(ew.hasMoreElements()){
        ssss=ew.nextElement().toString();
        in.write(ssss+"\n");
        }

        in.close();
        vec.clear();

        }catch(Exception ee){
        ee.printStackTrace();
        }

        }


        }
