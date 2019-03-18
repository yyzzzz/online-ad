package com.yyzzzz.ad.service.impl;

import com.yyzzzz.ad.dao.CreativeRepository;
import com.yyzzzz.ad.domain.Creative;
import com.yyzzzz.ad.exception.AdException;
import com.yyzzzz.ad.service.ICreativeService;
import com.yyzzzz.ad.vo.CreativeRequest;
import com.yyzzzz.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yyzzzz on 2019/3/18.
 */
@Service
public class CreativeServiceImpl implements ICreativeService {

    @Autowired
    private CreativeRepository creativeRepository;

    @Override
    public CreativeResponse createCreative(CreativeRequest request) throws AdException {

        Creative creative = creativeRepository.save(request.toEntity());
        return new CreativeResponse(creative.getId(), creative.getName());
    }
}
