package com.yyzzzz.ad.service.impl;

import com.yyzzzz.ad.constants.CommonEnum;
import com.yyzzzz.ad.dao.AdPlanRepository;
import com.yyzzzz.ad.dao.AdUserRepository;
import com.yyzzzz.ad.domain.AdPlan;
import com.yyzzzz.ad.domain.AdUser;
import com.yyzzzz.ad.exception.AdException;
import com.yyzzzz.ad.service.IAdPlanService;
import com.yyzzzz.ad.utils.CommonUtil;
import com.yyzzzz.ad.vo.AdPlanCriteria;
import com.yyzzzz.ad.vo.AdPlanRequest;
import com.yyzzzz.ad.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by yyzzzz on 2019/3/16.
 */
@Service
public class AdPlanServiceImpl implements IAdPlanService {

    @Autowired
    private AdPlanRepository adPlanRepository;

    @Autowired
    private AdUserRepository userRepository;

    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        if(!request.createValidate()) throw new AdException(CommonEnum.REQUEST_PARAM_ERROR.getDesc());
        Optional<AdUser> adUser = userRepository.findById(request.getId());
        adUser.orElseThrow(() -> new AdException(CommonEnum.CAN_NOT_FIND_RECORD.getDesc()));
        AdPlan adPlan = adPlanRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if(adPlan != null) throw new AdException(CommonEnum.SAME_PLAN_NAME_ERROR.getDesc());

        AdPlan save = adPlanRepository.save(new AdPlan(request.getUserId(), request.getPlanName(),
                CommonUtil.parseString2Date(request.getStartDate()),
                CommonUtil.parseString2Date(request.getEndDate())));
        return new AdPlanResponse(save.getId(), save.getPlanName());
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanCriteria criteria) throws AdException {
        if(!criteria.validate()) throw new AdException(CommonEnum.REQUEST_PARAM_ERROR.getDesc());
        return adPlanRepository.findAllByIdInAndUserId(criteria.getIds(), criteria.getUserId());
    }

    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        if(!request.updateValidate()) throw new AdException(CommonEnum.REQUEST_PARAM_ERROR.getDesc());

        AdPlan adPlan = adPlanRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if(adPlan == null) throw new AdException(CommonEnum.CAN_NOT_FIND_RECORD.getDesc());
        if (request.getPlanName() != null) adPlan.setPlanName(request.getPlanName());
        if (request.getStartDate() != null) adPlan.setStartDate(CommonUtil.parseString2Date(request.getStartDate()));
        if (request.getEndDate() != null) adPlan.setEndDate(CommonUtil.parseString2Date(request.getEndDate()));
        adPlan.setUpdateTime(new Date());
        adPlan = adPlanRepository.save(adPlan);
        return new AdPlanResponse(adPlan.getId(), adPlan.getPlanName());
    }

    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        if (request.deleteValidate()) throw new AdException(CommonEnum.REQUEST_PARAM_ERROR.getDesc());
        AdPlan adPlan = adPlanRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if(adPlan == null) throw new AdException(CommonEnum.CAN_NOT_FIND_RECORD.getDesc());
        adPlan.setPlanStatus(CommonEnum.INVALID.getStatus());
        adPlan.setUpdateTime(new Date());
        adPlanRepository.save(adPlan);
    }
}
