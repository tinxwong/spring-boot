package com.tinx.java.chipin.core.sercet;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;

/**
 * @author tinx
 * @date 2018-10-4 18:31
 */
public class SecretGuid {

    public static String getGuid() {
        String guid = "";
        //创建一个脚本引擎管理器
        ScriptEngineManager manager = new ScriptEngineManager();
        //获取一个指定的名称的脚本引擎
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            //获取当前类的所在目录的路径
            String path = SecretGuid.class.getResource("").getPath();
            // FileReader的参数为所要执行的js文件的路径
            engine.eval(new FileReader(path + "SecretGuid.js"));
            if (engine instanceof Invocable) {
                Invocable invocable = (Invocable) engine;
                //从脚本引擎中返回一个给定接口的实现
                Methods executeMethod = invocable.getInterface(Methods.class);
                //执行指定的js方法
                guid = executeMethod.encodeInp();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return guid;
    }

}
