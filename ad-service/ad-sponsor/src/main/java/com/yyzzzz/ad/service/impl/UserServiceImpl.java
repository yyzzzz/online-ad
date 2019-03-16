package com.yyzzzz.ad.service.impl;

import com.yyzzzz.ad.constants.CommonEnum;
import com.yyzzzz.ad.dao.AdUserRepository;
import com.yyzzzz.ad.domain.AdUser;
import com.yyzzzz.ad.exception.AdException;
import com.yyzzzz.ad.service.IUserService;
import com.yyzzzz.ad.utils.CommonUtil;
import com.yyzzzz.ad.vo.UserRequest;
import com.yyzzzz.ad.vo.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yyzzzz on 2019/3/16.
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private AdUserRepository adUserRepository;

    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) throws AdException {

        if (!request.validate()){
            throw new AdException(CommonEnum.REQUEST_PARAM_ERROR.getDesc());
        }

        AdUser oldUser = adUserRepository.findByUsername(request.getUsername());
        if(oldUser != null){
            throw new AdException(CommonEnum.SAME_NAME_ERROR.getDesc());
        }

        AdUser adUser = adUserRepository.save(new AdUser(request.getUsername(), CommonUtil.md5(request.getUsername())));
        return new UserResponse(adUser.getId(), adUser.getUsername(), adUser.getToken(), adUser.getCreateTime(), adUser.getUpdateTime());
    }
}
