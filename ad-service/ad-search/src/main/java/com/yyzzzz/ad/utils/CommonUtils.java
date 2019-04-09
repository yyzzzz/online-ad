package com.yyzzzz.ad.utils;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by yyzzzz on 2019/4/8.
 */
public class CommonUtils {

    public static <K, V> V getOrCreate(K key, Map<K, V> map, Supplier<V> factory){
        return map.computeIfAbsent(key, k -> factory.get());
    }

    public static String stringConcat(String... args){
        StringBuilder stringBuilder = new StringBuilder();
        for (String arg : args){
            stringBuilder.append(arg);
            stringBuilder.append("-");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
