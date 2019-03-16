package com.yyzzzz.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by yyzzzz on 2019/3/16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long userId;
    private String username;
    private String token;
    private Date createTime;
    private Date updateTime;
}
