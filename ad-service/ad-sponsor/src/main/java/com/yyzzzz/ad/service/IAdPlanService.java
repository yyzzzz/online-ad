package com.yyzzzz.ad.service;

import com.yyzzzz.ad.domain.AdPlan;
import com.yyzzzz.ad.exception.AdException;
import com.yyzzzz.ad.vo.AdPlanCriteria;
import com.yyzzzz.ad.vo.AdPlanRequest;
import com.yyzzzz.ad.vo.AdPlanResponse;

import java.util.List;

/**
 * Created by yyzzzz on 2019/3/16.
 */
public interface IAdPlanService {

    //创建推广计划
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    //获取推广计划
    List<AdPlan> getAdPlanByIds(AdPlanCriteria criteria) throws AdException;

    //更新推广计划
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    //删除推广计划
    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
