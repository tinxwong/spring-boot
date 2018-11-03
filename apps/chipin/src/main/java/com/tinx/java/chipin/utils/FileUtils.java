package com.tinx.java.chipin.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tinx
 * @date 2018-10-15 20:52
 */
public class FileUtils {

    public static boolean createFile(String path,String filename){

        File file=new File(path);
        file.mkdirs();
        try{
            File file1 = new File(path+filename);
            file1.createNewFile();
            return true;
        }catch(IOException e){
            return false;
        }
    }


    public static boolean writeToFile(String path,String content){
        try{
            File file = new File(path);

            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static String readFromFile(String path){
        File file = new File(path);
        StringBuffer content = new StringBuffer();
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));

            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                content.append(tempString).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return content.toString();
    }

    public static List<String> fileContentToList(String path){
        List<String> list = new ArrayList<>();

        File file = new File(path);
        StringBuffer content = new StringBuffer();
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));

            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                tempString=tempString.replaceAll(" +"," ");
                tempString=tempString.replaceAll("\t+"," ");
                String[] arrTempString = tempString.split(" ");
                List<String> temps = Arrays.asList(arrTempString);
                list.addAll(temps);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return list;
    }
}
