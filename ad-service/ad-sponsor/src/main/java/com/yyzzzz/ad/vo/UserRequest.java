package com.yyzzzz.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * Created by yyzzzz on 2019/3/16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String username;

    public boolean validate(){
        return !StringUtils.isEmpty(username);
    }
}
