package com.tinx.java.chipin.utils;

import java.util.Random;

/**
 * @author tinx
 * @date 2018-10-7 21:20
 */
public class RandomUtils {

    public static String getUrl(String url){
        String[] arrUrl = url.split(",");
        Random rand = new Random();
        int n =rand.nextInt(arrUrl.length);
        return arrUrl[n];
    }
}
