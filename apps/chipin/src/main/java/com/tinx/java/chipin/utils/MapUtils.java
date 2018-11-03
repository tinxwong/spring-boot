package com.tinx.java.chipin.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tinx
 * @date 2018-9-9 21:31
 */
public class MapUtils {

    private static final Map<String,Object> map = new ConcurrentHashMap<>();

    public static void put(String key ,Object value){
        map.put(key,value);
    }

    public static Object get(String key){
        return map.get(key);
    }

    public static Object remove(String key){
        return map.remove(key);
    }
}
