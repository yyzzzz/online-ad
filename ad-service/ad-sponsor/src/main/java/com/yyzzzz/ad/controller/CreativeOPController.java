package com.yyzzzz.ad.controller;

import com.alibaba.fastjson.JSON;
import com.yyzzzz.ad.exception.AdException;
import com.yyzzzz.ad.service.ICreativeService;
import com.yyzzzz.ad.vo.CreativeRequest;
import com.yyzzzz.ad.vo.CreativeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yyzzzz on 2019/3/18.
 */
@Slf4j
@RestController
public class CreativeOPController {

    @Autowired
    private ICreativeService creativeService;

    @PostMapping("creative")
    public CreativeResponse createCreative(@RequestBody CreativeRequest request) throws AdException {
        log.info("ad-sponsor: create creative -> {}", JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }
}
