package com.yyzzzz.ad.handler;

import com.alibaba.fastjson.JSON;
import com.yyzzzz.ad.dump.table.*;
import com.yyzzzz.ad.index.DataTable;
import com.yyzzzz.ad.index.IndexAware;
import com.yyzzzz.ad.index.Keyword.UnitKeywordIndex;
import com.yyzzzz.ad.index.adplan.AdPlanIndex;
import com.yyzzzz.ad.index.adplan.AdPlanObject;
import com.yyzzzz.ad.index.adplan.AdUnitObject;
import com.yyzzzz.ad.index.adunit.AdUnitIndex;
import com.yyzzzz.ad.index.creative.CreativeIndex;
import com.yyzzzz.ad.index.creative.CreativeObject;
import com.yyzzzz.ad.index.creativeunit.CreativeUnitIndex;
import com.yyzzzz.ad.index.creativeunit.CreativeUnitObject;
import com.yyzzzz.ad.index.district.UnitDistrictIndex;
import com.yyzzzz.ad.index.interest.UnitItIndex;
import com.yyzzzz.ad.mysql.constant.OpType;
import com.yyzzzz.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yyzzzz on 2019/4/9.
 */
@Slf4j
public class AdLevelData {

    public static void handleLevel2(AdPlanTable adPlanTable, OpType type){
        AdPlanObject adPlanObject = new AdPlanObject(
                adPlanTable.getId(),adPlanTable.getUserId(),adPlanTable.getPlanStatus(),adPlanTable.getStartDate(),adPlanTable.getEndADate()
        );
        handleBinlogEvent(DataTable.of(AdPlanIndex.class), adPlanObject.getPlanId(), adPlanObject, type);
    }

    public static void handleLevel2(CreativeTable creativeTable, OpType type){
        CreativeObject creativeObject = new CreativeObject(
                creativeTable.getAdId(),
                creativeTable.getName(),
                creativeTable.getType(),
                creativeTable.getMaterialType(),
                creativeTable.getHeight(),
                creativeTable.getWidth(),
                creativeTable.getAuditStatus(),
                creativeTable.getAdUrl()
        );
        handleBinlogEvent(DataTable.of(CreativeIndex.class), creativeObject.getAdId(), creativeObject, type);
    }

    public static void handleLevel3(AdUnitTable unitTable, OpType type){
        AdPlanObject adPlanObject = DataTable.of(AdPlanIndex.class).get(unitTable.getPlanId());
        if(null == adPlanObject) {
            log.error("handlelevel3 found AdPlanObject error: {}", unitTable.getPlanId());
            return;
        }

        AdUnitObject adUnitObject = new AdUnitObject(
                unitTable.getUnitId(),unitTable.getUnitStatus(),unitTable.getPositionType(),unitTable.getPlanId(),adPlanObject
        );

        handleBinlogEvent(DataTable.of(AdUnitIndex.class), adUnitObject.getUnitId(), adUnitObject, type);
    }

    public static void handleLevel3(AdCreativeUnitTable adCreativeUnitTable, OpType type){
        if(type == OpType.UPDATE) {
            log.error("AdCreativeUnitTable not support update");
            return;
        }

        AdUnitObject unitObject = DataTable.of(AdUnitIndex.class).get(adCreativeUnitTable.getUnitId());
        CreativeObject creativeObject = DataTable.of(CreativeIndex.class).get(adCreativeUnitTable.getAdId());
        if(null == unitObject || null == creativeObject) {
            log.error("AdCreativeUnitTable index error: {}", JSON.toJSONString(adCreativeUnitTable));
            return;
        }

        CreativeUnitObject creativeUnitObject = new CreativeUnitObject(
                adCreativeUnitTable.getAdId(),
                adCreativeUnitTable.getUnitId()
        );

        handleBinlogEvent(DataTable.of(CreativeUnitIndex.class),
                CommonUtils.stringConcat(creativeUnitObject.getAdId().toString(),
                        creativeUnitObject.getUnitId().toString()), creativeUnitObject, type);
    }

    public static void handleLevel4(AdUnitDistrictTable adUnitDistrictTable, OpType type){
        if(type == OpType.UPDATE) {
            log.error("district index can not support update");
            return;
        }

        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(adUnitDistrictTable.getUnitId());
        if(null == adUnitObject) {
            log.error("AdUnitDistrictTable index error: {}", adUnitDistrictTable.getUnitId());
            return;
        }

        String key = CommonUtils.stringConcat(adUnitDistrictTable.getProvince(), adUnitDistrictTable.getCity());
        Set<Long> value = new HashSet<>(
                Collections.singleton(adUnitDistrictTable.getUnitId())
        );
        handleBinlogEvent(DataTable.of(UnitDistrictIndex.class), key, value, type);
    }

    public static void handleLevel4(AdUnitItTable adUnitItTable, OpType type){
        if(type == OpType.UPDATE) {
            log.error("adUnitItTable index can not support update");
            return;
        }

        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(adUnitItTable.getUnitId());
        if(null == adUnitObject) {
            log.error("AdUnitItTable index error: {}", adUnitItTable.getUnitId());
            return;
        }

        Set<Long> value = new HashSet<>(
                Collections.singleton(adUnitItTable.getUnitId())
        );
        handleBinlogEvent(DataTable.of(UnitItIndex.class), adUnitItTable.getItTag(), value, type);
    }

    public static void handleLevel4(AdUnitKeywordTable adUnitKeywordTable, OpType type){
        if(type == OpType.UPDATE) {
            log.error("adUnitKeywordTable index can not support update");
            return;
        }

        AdUnitObject adUnitObject = DataTable.of(AdUnitIndex.class).get(adUnitKeywordTable.getUnitId());
        if(null == adUnitObject) {
            log.error("AdUnitKeywordTable index error: {}", adUnitKeywordTable.getUnitId());
            return;
        }

        Set<Long> value = new HashSet<>(
                Collections.singleton(adUnitKeywordTable.getUnitId())
        );
        handleBinlogEvent(DataTable.of(UnitKeywordIndex.class), adUnitKeywordTable.getKeyword(), value, type);
    }

    private static <K, V> void handleBinlogEvent(IndexAware<K, V> indexAware, K key, V value, OpType type){

        switch (type){
            case INSERT:
                indexAware.add(key, value);
                break;
            case UPDATE:
                indexAware.update(key, value);
                break;
            case DELETE:
                indexAware.delete(key, value);
                break;
            default:
                break;
        }
    }
}
