package com.tinx.java.chipin.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tinx
 * @date 2018-8-28 10:05
 */
public class MyBeanUtils {

    public static <T> T convertBean(Object orig, Class destClazz) {
        if(orig == null){
            return null;
        }

        Object dest = null;
        try {
            dest = destClazz.newInstance();
            org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return (T) dest;
    }

    public static <T> List<T> convertBeanList(List origList, Class destClazz) {
        if(origList == null){
            return null;
        }

        List<T> destList = new ArrayList<T>();
        for(Object orig:origList){
            T obj = convertBean(orig, destClazz);
            destList.add(obj);
        }
        return destList;
    }
}
