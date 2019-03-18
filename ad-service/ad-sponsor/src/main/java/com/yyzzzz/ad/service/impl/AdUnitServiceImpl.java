package com.yyzzzz.ad.service.impl;

import com.yyzzzz.ad.constants.CommonEnum;
import com.yyzzzz.ad.dao.AdPlanRepository;
import com.yyzzzz.ad.dao.AdUnitRepository;
import com.yyzzzz.ad.dao.CreativeRepository;
import com.yyzzzz.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.yyzzzz.ad.dao.unit_condition.AdUnitItRepository;
import com.yyzzzz.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.yyzzzz.ad.dao.unit_condition.CreativeUnitRepository;
import com.yyzzzz.ad.domain.AdPlan;
import com.yyzzzz.ad.domain.AdUnit;
import com.yyzzzz.ad.domain.unit_condition.AdUnitDistrict;
import com.yyzzzz.ad.domain.unit_condition.AdUnitIt;
import com.yyzzzz.ad.domain.unit_condition.AdUnitKeyword;
import com.yyzzzz.ad.domain.unit_condition.CreativeUnit;
import com.yyzzzz.ad.exception.AdException;
import com.yyzzzz.ad.service.IAdUnitService;
import com.yyzzzz.ad.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by yyzzzz on 2019/3/17.
 */
@Service
public class AdUnitServiceImpl implements IAdUnitService {

    @Autowired
    private AdUnitRepository unitRepository;

    @Autowired
    private AdPlanRepository planRepository;

    @Autowired
    private AdUnitDistrictRepository unitDistrictRepository;

    @Autowired
    private AdUnitItRepository itRepository;

    @Autowired
    private AdUnitKeywordRepository keywordRepository;

    @Autowired
    private CreativeRepository creativeRepository;

    @Autowired
    private CreativeUnitRepository creativeUnitRepository;

    @Override
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {

        if (!request.createValidate()) throw new AdException(CommonEnum.REQUEST_PARAM_ERROR.getDesc());
        Optional<AdPlan> adPlan = planRepository.findById(request.getPlanId());
        adPlan.orElseThrow(() -> new AdException(CommonEnum.CAN_NOT_FIND_RECORD.getDesc()));

        AdUnit adUnit = unitRepository.findByPlanIdAndUnitName(request.getPlanId(), request.getUnitName());
        if (adUnit != null) throw new AdException(CommonEnum.SAME_PLAN_NAME_ERROR.getDesc());

        AdUnit save = unitRepository.save(new AdUnit(request.getPlanId(), request.getUnitName(), request.getPositionType(), request.getBudget()));

        return new AdUnitResponse(save.getId(), save.getUnitName());
    }

    @Override
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {

        List<Long> unitIds = request.getUnitKeywords().stream()
                .map(AdUnitKeywordRequest.UnitKeyword::getUnitId)
                .collect(Collectors.toList());
        if (!isReleatedUnitExist(unitIds)) throw new AdException(CommonEnum.REQUEST_PARAM_ERROR.getDesc());
        List<AdUnitKeyword> keywords = new ArrayList<>();
        request.getUnitKeywords().forEach(i -> keywords.add(new AdUnitKeyword(i.getUnitId(), i.getKeyword())));
        List<Long> ids = keywordRepository.saveAll(keywords).stream().map(AdUnitKeyword::getId).collect(Collectors.toList());
        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {

        List<Long> collect = request.getUnitIts().stream().map(AdUnitItRequest.UnitIt::getUnitId).collect(Collectors.toList());
        if (!isReleatedUnitExist(collect)) throw new AdException(CommonEnum.REQUEST_PARAM_ERROR.getDesc());

        List<AdUnitIt> unitIts = new ArrayList<>();
        request.getUnitIts().forEach(i -> unitIts.add(new AdUnitIt(i.getUnitId(), i.getItTag())));
        List<Long> ids = itRepository.saveAll(unitIts).stream().map(AdUnitIt::getId).collect(Collectors.toList());
        return new AdUnitItResponse(ids);
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {

        List<Long> collect = request.getUnitDistricts().stream()
                .map(AdUnitDistrictRequest.UnitDistrict::getUnitId)
                .collect(Collectors.toList());
        if (!isReleatedUnitExist(collect)) throw new AdException(CommonEnum.REQUEST_PARAM_ERROR.getDesc());
        List<AdUnitDistrict> districts = new ArrayList<>();
        request.getUnitDistricts().forEach(district -> new AdUnitDistrict(district.getUnitId(), district.getProvince(), district.getCity()));
        List<Long> ids = unitDistrictRepository.saveAll(districts).stream().
                map(AdUnitDistrict::getId).
                collect(Collectors.toList());
        return new AdUnitDistrictResponse(ids);
    }

    @Override
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {
        List<Long> unitIds = request.getItems().stream().map(CreativeUnitRequest.CreativeUnitItem::getUnitId).collect(Collectors.toList());
        List<Long> creativeIds = request.getItems().stream().map(CreativeUnitRequest.CreativeUnitItem::getCreativeId).collect(Collectors.toList());

        if (!(isReleatedUnitExist(unitIds) && isReleatedCreativeExist(creativeIds)))
            throw new AdException(CommonEnum.REQUEST_PARAM_ERROR.getDesc());
        List<CreativeUnit> creativeUnits = new ArrayList<>();
        request.getItems().forEach(item -> creativeUnits.add(new CreativeUnit(item.getCreativeId(), item.getUnitId())));

        List<Long> ids = creativeUnitRepository.saveAll(creativeUnits).stream()
                .map(CreativeUnit::getId)
                .collect(Collectors.toList());
        return new CreativeUnitResponse(ids);
    }

    private boolean isReleatedUnitExist(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return false;
        }
        return unitRepository.findAllById(ids).size() == new HashSet<>(ids).size();
    }

    private boolean isReleatedCreativeExist(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) return false;

        return creativeRepository.findAllById(ids).size() == new HashSet<>(ids).size();
    }
}
