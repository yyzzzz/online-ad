package com.yyzzzz.ad.controller;

import com.alibaba.fastjson.JSON;
import com.yyzzzz.ad.exception.AdException;
import com.yyzzzz.ad.service.IUserService;
import com.yyzzzz.ad.vo.UserRequest;
import com.yyzzzz.ad.vo.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yyzzzz on 2019/3/18.
 */
@Slf4j
@RestController
public class UserOPController {

    @Autowired
    private IUserService userService;

    @PostMapping("/user")
    public UserResponse createUser(@RequestBody UserRequest request) throws AdException {
        log.info("ad-sponsor: create user -> {}", JSON.toJSONString(request));
        return userService.createUser(request);
    }
}
