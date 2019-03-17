package com.yyzzzz.ad.service.impl;

import com.yyzzzz.ad.constants.CommonEnum;
import com.yyzzzz.ad.dao.AdPlanRepository;
import com.yyzzzz.ad.dao.AdUnitRepository;
import com.yyzzzz.ad.domain.AdPlan;
import com.yyzzzz.ad.domain.AdUnit;
import com.yyzzzz.ad.exception.AdException;
import com.yyzzzz.ad.service.IAdUnitService;
import com.yyzzzz.ad.vo.AdUnitRequest;
import com.yyzzzz.ad.vo.AdUnitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by yyzzzz on 2019/3/17.
 */
@Service
public class AdUnitServiceImpl implements IAdUnitService {

    @Autowired
    private AdUnitRepository unitRepository;

    @Autowired
    private AdPlanRepository planRepository;

    @Override
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {

        if(!request.createValidate()) throw new AdException(CommonEnum.REQUEST_PARAM_ERROR.getDesc());
        Optional<AdPlan> adPlan = planRepository.findById(request.getPlanId());
        adPlan.orElseThrow(() -> new AdException(CommonEnum.CAN_NOT_FIND_RECORD.getDesc()));

        AdUnit adUnit = unitRepository.findByPlanIdAndUnitName(request.getPlanId(), request.getUnitName());
        if(adUnit != null) throw new AdException(CommonEnum.SAME_PLAN_NAME_ERROR.getDesc());

        AdUnit save = unitRepository.save(new AdUnit(request.getPlanId(), request.getUnitName(), request.getPositionType(), request.getBudget()));

        return new AdUnitResponse(save.getId(), save.getUnitName());
    }
}
