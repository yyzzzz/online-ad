package com.yyzzzz.ad.service;

import com.yyzzzz.ad.exception.AdException;
import com.yyzzzz.ad.vo.CreativeRequest;
import com.yyzzzz.ad.vo.CreativeResponse;

/**
 * Created by yyzzzz on 2019/3/18.
 */
public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request) throws AdException;
}
