package com.yyzzzz.ad.service;

import com.yyzzzz.ad.exception.AdException;
import com.yyzzzz.ad.vo.AdUnitRequest;
import com.yyzzzz.ad.vo.AdUnitResponse;

/**
 * Created by yyzzzz on 2019/3/17.
 */
public interface IAdUnitService {

    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;
}
