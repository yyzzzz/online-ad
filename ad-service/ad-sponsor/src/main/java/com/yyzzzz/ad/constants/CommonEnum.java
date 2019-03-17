package com.yyzzzz.ad.constants;

import lombok.Getter;

/**
 * Created by yyzzzz on 2019/3/14.
 */
@Getter
public enum CommonEnum {

    VALID(1, "有效状态"),
    INVALID(0, "无效状态"),

    //请求参数
    REQUEST_PARAM_ERROR(400, "请求参数错误"),

    //用户相关
    SAME_NAME_ERROR(10, "用户已存在"),
    SAME_PLAN_NAME_ERROR(40, "推广计划已存在"),
    SAME_UNIT_NAME_ERROR(41, "推广单元已存在"),
    CAN_NOT_FIND_RECORD(50, "无对应记录");

    private Integer status;
    private String desc;

    CommonEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
