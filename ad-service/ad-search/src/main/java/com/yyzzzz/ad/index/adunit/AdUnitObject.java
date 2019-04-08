package com.yyzzzz.ad.index.adplan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by yyzzzz on 2019/4/8.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitObject {
    private Long unitId;
    private Integer unitStatus;
    private Integer positionType;
    private Long planId;

    private AdPlanObject adPlanObject;

    public void update(AdUnitObject newObject){
        if (null != newObject.getUnitId()){
            this.unitId = newObject.getUnitId();
        }
        if(null != newObject.getPlanId()){
            this.planId = newObject.getPlanId();
        }
        if(null != newObject.getUnitStatus()){
            this.unitStatus = newObject.getUnitStatus();
        }
        if(null != newObject.getPositionType()){
            this.positionType = newObject.getPositionType();
        }
        if(null != newObject.getAdPlanObject()){
            this.adPlanObject = newObject.getAdPlanObject();
        }

    }
}
