package com.yyzzzz.ad.dao;

import com.yyzzzz.ad.domain.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yyzzzz on 2019/3/16.
 */
public interface AdUnitRepository extends JpaRepository<AdUnit, Long>{

    AdUnit findByPlanIdAndUnitName(Long planId, String unitName);

    List<AdUnit> findAllByUnitStatus(Integer unitStatus);
}
