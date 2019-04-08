package com.yyzzzz.ad.index.adunit;

import com.yyzzzz.ad.index.IndexAware;
import com.yyzzzz.ad.index.adplan.AdUnitObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yyzzzz on 2019/4/8.
 */
@Slf4j
@Component
public class AdUnitIndex implements IndexAware<Long, AdUnitObject> {

    private static Map<Long, AdUnitObject> objectMap;

    static {
        objectMap = new ConcurrentHashMap<>();
    }

    @Override
    public AdUnitObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, AdUnitObject value) {
        log.info("before add: {}", objectMap);
        objectMap.put(key, value);
        log.info("after add: {}", objectMap);
    }

    @Override
    public void update(Long key, AdUnitObject value) {
        log.info("before update: {}", objectMap);
        AdUnitObject object = objectMap.get(key);
        if (null == object) {
            objectMap.put(key, value);
        } else {
            object.update(value);
        }
        log.info("after update: {}", objectMap);

    }

    @Override
    public void delete(Long key, AdUnitObject value) {
        log.info("before remove: {}", objectMap);
        objectMap.remove(key);
        log.info("after remove: {}", objectMap);

    }
}
