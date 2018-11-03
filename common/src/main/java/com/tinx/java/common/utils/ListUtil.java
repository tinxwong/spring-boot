package com.tinx.java.common.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class ListUtil {

    private ListUtil() {}

    public static boolean isPresent(Collection list) {
        return list != null && !list.isEmpty();
    }

    public static boolean isBlank(Collection list) {
        return list == null || list.isEmpty();
    }

    public static String join(Collection list, String sep) {
        if (isBlank(list)) {
            return "";
        }

        String prefix = "";
        StringBuffer buffer = new StringBuffer();
        for(Iterator<Object> iter = list.iterator(); iter.hasNext(); prefix = sep) {
            buffer.append(prefix).append(iter.next());
        }
        return buffer.toString();
    }

    public static <T> List<T> map(Collection list, String field) {
        List<T> result = new ArrayList<T>();
        try {
            String methodName = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
            for (Object value : list) {
                Method method = value.getClass().getMethod(methodName);
                T fieldValue = (T) method.invoke(value);
                result.add(fieldValue);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("对象没有定义" + field);
        }
        return result;
    }

    public static <T, E> List<T> map(Collection<E> list, IteratorTask<T, E> runnable) {
        List<T> result = new ArrayList<>(list.size());
        for (E item : list) {
            result.add(runnable.getValue(item));
        }
        return result;
    }

    public interface IteratorTask<TargetType, SourceType> {
        TargetType getValue(SourceType item);
    }
}
