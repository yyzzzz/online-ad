package com.yyzzzz.ad.service;

import com.yyzzzz.ad.exception.AdException;
import com.yyzzzz.ad.vo.UserRequest;
import com.yyzzzz.ad.vo.UserResponse;

/**
 * Created by yyzzzz on 2019/3/16.
 */
public interface IUserService {

    UserResponse createUser(UserRequest request) throws AdException;

}
