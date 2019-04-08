package com.yyzzzz.ad.index;

/**
 * Created by yyzzzz on 2019/4/8.
 */
public interface IndexAware<K, V> {

    V get(K key);

    void add(K key, V value);

    void update(K key, V value);

    void delete(K key, V value);
}
