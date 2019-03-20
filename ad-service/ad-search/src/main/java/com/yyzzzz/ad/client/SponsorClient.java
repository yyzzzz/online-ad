package com.yyzzzz.ad.client;

import com.yyzzzz.ad.client.vo.AdPLan;
import com.yyzzzz.ad.client.vo.AdPlanGetRequest;
import com.yyzzzz.ad.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by yyzzzz on 2019/3/20.
 */
@FeignClient(value = "eureka-client-ad-sponsor" , fallback = SponsorClientHystrix.class)
public interface SponsorClient {

    @RequestMapping(value = "/ad-sponsor/get/adPlan", method = RequestMethod.POST)
    CommonResponse<List<AdPLan>> getAdPlans(@RequestBody AdPlanGetRequest request);

}