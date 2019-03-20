package com.yyzzzz.ad.controller;

import com.alibaba.fastjson.JSON;
import com.yyzzzz.ad.client.SponsorClient;
import com.yyzzzz.ad.annotation.IgnoreResponseAdvice;
import com.yyzzzz.ad.client.vo.AdPLan;
import com.yyzzzz.ad.client.vo.AdPlanGetRequest;
import com.yyzzzz.ad.vo.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by yyzzzz on 2019/3/19.
 */
@Slf4j
@RestController
public class SearchController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SponsorClient sponsorClient;

    @IgnoreResponseAdvice
    @PostMapping("/get")
    public CommonResponse<List<AdPLan>> getAdPlans(@RequestBody AdPlanGetRequest request){
        log.info("ad-search : get ad plans -> {}", JSON.toJSONString(request));
        return sponsorClient.getAdPlans(request);
    }

    @IgnoreResponseAdvice
    @PostMapping("/getAdPlansByRibbon")
    public CommonResponse<List<AdPLan>> getAdPlansByRibbon(@RequestBody AdPlanGetRequest request) {
        log.info("ad-search: getAdPlansByRebbon -> {}", JSON.toJSONString(request));
        return restTemplate.postForEntity("http://eureka-client-ad-sponsor/ad-sponsor/get/adPLan",
                request, CommonResponse.class).getBody();
    }
}
