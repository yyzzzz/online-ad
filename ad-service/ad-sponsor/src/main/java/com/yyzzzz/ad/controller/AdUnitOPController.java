package com.yyzzzz.ad.controller;

import com.alibaba.fastjson.JSON;
import com.yyzzzz.ad.exception.AdException;
import com.yyzzzz.ad.service.IAdUnitService;
import com.yyzzzz.ad.vo.*;
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
public class AdUnitOPController {

    @Autowired
    private IAdUnitService unitService;

    @PostMapping("adUnit")
    public AdUnitResponse createUnit(@RequestBody AdUnitRequest request) throws AdException {
        log.info("ad-sponsor: create unit -> {}", JSON.toJSONString(request));
        return unitService.createUnit(request);
    }

    @PostMapping("unitKeyword")
    public AdUnitKeywordResponse createUnitKeyword(@RequestBody AdUnitKeywordRequest request) throws AdException {
        log.info("ad-sponsor: create unitKeyword -> {}", JSON.toJSONString(request));
        return unitService.createUnitKeyword(request);
    }

    @PostMapping("unitIt")
    public AdUnitItResponse createUnitIt(@RequestBody AdUnitItRequest request) throws AdException {
        log.info("ad-sponsor: create unitIt -> {}", JSON.toJSONString(request));
        return unitService.createUnitIt(request);
    }

    @PostMapping("unitDistrict")
    public AdUnitDistrictResponse createUnitDistrict(@RequestBody AdUnitDistrictRequest request) throws AdException {
        log.info("ad-sponsor: create unitDistrict -> {}", JSON.toJSONString(request));
        return unitService.createUnitDistrict(request);
    }

    @PostMapping("creativeUnit")
    public CreativeUnitResponse createUnitDistrict(@RequestBody CreativeUnitRequest request) throws AdException {
        log.info("ad-sponsor: create creativeUnit -> {}", JSON.toJSONString(request));
        return unitService.createCreativeUnit(request);
    }
}
