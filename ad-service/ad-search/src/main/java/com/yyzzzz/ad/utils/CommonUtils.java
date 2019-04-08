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
}
