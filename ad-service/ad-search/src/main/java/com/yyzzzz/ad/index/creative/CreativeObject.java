package com.yyzzzz.ad.index.creative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yyzzzz on 2019/4/8.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeObject {

    private Long adId;
    private String name;
    private Integer type;
    private Integer materialType;
    private Integer height;
    private Integer width;
    private Integer auditStatus;
    private String adUrl;

    public void update(CreativeObject creativeObject) {

        if (null != creativeObject.getAdId()) {
            this.adId = creativeObject.getAdId();
        }
        if (null != creativeObject.getName()) {
            this.name = creativeObject.getName();
        }
        if (null != creativeObject.getType()) {
            this.type = creativeObject.getType();
        }
        if (null != creativeObject.getMaterialType()) {
            this.materialType = creativeObject.getMaterialType();
        }
        if (null != creativeObject.getHeight()) {
            this.height = creativeObject.getHeight();
        }
        if (null != creativeObject.getWidth()) {
            this.width = creativeObject.getWidth();
        }
        if (null != creativeObject.getAuditStatus()) {
            this.auditStatus = creativeObject.getAuditStatus();
        }
        if (null != creativeObject.getAdUrl()) {
            this.adUrl = creativeObject.getAdUrl();
        }

    }
}
