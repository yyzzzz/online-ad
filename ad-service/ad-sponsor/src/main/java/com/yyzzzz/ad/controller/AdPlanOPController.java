package com.yyzzzz.ad.controller;

import com.alibaba.fastjson.JSON;
import com.yyzzzz.ad.domain.AdPlan;
import com.yyzzzz.ad.exception.AdException;
import com.yyzzzz.ad.service.IAdPlanService;
import com.yyzzzz.ad.vo.AdPlanCriteria;
import com.yyzzzz.ad.vo.AdPlanRequest;
import com.yyzzzz.ad.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yyzzzz on 2019/3/18.
 */
@Slf4j
@RestController
public class AdPlanOPController {

    @Autowired
    private IAdPlanService planService;

    @PostMapping("/adplan")
    public AdPlanResponse createAdPlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: create adplan -> {}" , JSON.toJSONString(request));
        return planService.createAdPlan(request);
    }

    @PostMapping("/get/adPlan")
    public List<AdPlan> getAdPlanIds(@RequestBody AdPlanCriteria criteria) throws AdException{
        log.info("ad-sponsor: get ad plan by ids -> {}", JSON.toJSONString(criteria));
        return planService.getAdPlanByIds(criteria);
    }

    @PutMapping("adplan")
    public AdPlanResponse updateAdPlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: update ad plan -> {}", JSON.toJSONString(request));
        return planService.updateAdPlan(request);
    }

    @DeleteMapping("adplan")
    public void deleteAdPlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: delete ad plan -> {}", JSON.toJSONString(request));
        planService.deleteAdPlan(request);
    }
}
