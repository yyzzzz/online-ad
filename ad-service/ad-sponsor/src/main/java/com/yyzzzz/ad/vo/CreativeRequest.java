package com.yyzzzz.ad.vo;

import com.yyzzzz.ad.constants.CommonEnum;
import com.yyzzzz.ad.domain.Creative;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by yyzzzz on 2019/3/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreativeRequest {

    private String name;

    private Integer type;

    private Integer materialType;

    private Integer height;

    private Integer width;

    private Long size;

    private Integer duration;

    private Long userId;

    private String url;

    public Creative toEntity(){
        Creative creative = new Creative();
        creative.setName(name);
        creative.setType(type);
        creative.setMaterialType(materialType);
        creative.setHeight(height);
        creative.setWidth(width);
        creative.setSize(size);
        creative.setDuration(duration);
        creative.setAuditStatus(CommonEnum.VALID.getStatus());
        creative.setUserId(userId);
        creative.setUrl(url);
        creative.setCreateTime(new Date());
        creative.setUpdateTime(creative.getCreateTime());

        return creative;
    }
}
