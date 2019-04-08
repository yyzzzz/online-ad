package com.yyzzzz.ad.index.creative;

import com.yyzzzz.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yyzzzz on 2019/4/8.
 */
@Slf4j
@Component
public class CreativeIndex implements IndexAware<Long, CreativeObject> {

    private static Map<Long, CreativeObject> map;

    static {
        map = new ConcurrentHashMap<>();
    }

    @Override
    public CreativeObject get(Long key) {
        return map.get(key);
    }

    @Override
    public void add(Long key, CreativeObject value) {
        log.info("before add: {}", map);
        map.put(key, value);
        log.info("after add: {}", map);
    }

    @Override
    public void update(Long key, CreativeObject value) {
        log.info("before update: {}", map);
        CreativeObject creativeObject = map.get(key);
        if (null == creativeObject) {
            map.put(key, value);
        } else {
            creativeObject.update(value);
        }
        log.info("after update: {}", map);

    }

    @Override
    public void delete(Long key, CreativeObject value) {
        log.info("before delete: {}", map);
        map.remove(key);
        log.info("after delete: {}", map);

    }
}
