package com.yyzzzz.ad.service;

import com.alibaba.fastjson.JSON;
import com.yyzzzz.ad.Application;
import com.yyzzzz.ad.constants.CommonEnum;
import com.yyzzzz.ad.dao.AdPlanRepository;
import com.yyzzzz.ad.dao.AdUnitRepository;
import com.yyzzzz.ad.dao.CreativeRepository;
import com.yyzzzz.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.yyzzzz.ad.dao.unit_condition.AdUnitItRepository;
import com.yyzzzz.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.yyzzzz.ad.dao.unit_condition.CreativeUnitRepository;
import com.yyzzzz.ad.domain.AdPlan;
import com.yyzzzz.ad.domain.AdUnit;
import com.yyzzzz.ad.domain.Creative;
import com.yyzzzz.ad.domain.unit_condition.AdUnitDistrict;
import com.yyzzzz.ad.domain.unit_condition.AdUnitIt;
import com.yyzzzz.ad.domain.unit_condition.AdUnitKeyword;
import com.yyzzzz.ad.domain.unit_condition.CreativeUnit;
import com.yyzzzz.ad.dump.DConstant;
import com.yyzzzz.ad.dump.table.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yyzzzz on 2019/4/9.
 */
@Slf4j
@RunWith((SpringRunner.class))
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DumpDataService {

    @Autowired
    private AdPlanRepository adPlanRepository;

    @Autowired
    private AdUnitRepository adUnitRepository;

    @Autowired
    private CreativeRepository creativeRepository;

    @Autowired
    private CreativeUnitRepository creativeUnitRepository;

    @Autowired
    private AdUnitItRepository adUnitItRepository;

    @Autowired
    private AdUnitKeywordRepository adUnitKeywordRepository;

    @Autowired
    private AdUnitDistrictRepository adUnitDistrictRepository;

    @Test
    public void dumpAdTableData(){
        dumpAdUnitTable(String.format("s%s%", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT));

        dumpAdPlanTable(String.format("s%s%", DConstant.DATA_ROOT_DIR, DConstant.AD_PLAN));

        dumpAdCreativeTable(String.format("s%s%", DConstant.DATA_ROOT_DIR, DConstant.AD_CREATIVE));

        dumpAdCreativeUnitTable(String.format("s%s%", DConstant.DATA_ROOT_DIR, DConstant.AD_CREATIVE_UNIT));

        dumpAdUnitDistrictTable(String.format("s%s%", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT_DISTRICT));

        dumpAdUnitItTable(String.format("s%s%", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT_IT));

        dumpAdUnitKeywordTable(String.format("s%s%", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT_KEYWORD));
    }

    private void dumpAdPlanTable(String fileName) {
        List<AdPlan> adPlans = adPlanRepository.findAllByPlanStatus(CommonEnum.VALID.getStatus());
        if (CollectionUtils.isEmpty(adPlans)) return;

        List<AdPlanTable> plans = new ArrayList<>();
        adPlans.forEach(adPlan -> plans.add(
                new AdPlanTable(adPlan.getId(), adPlan.getUserId(), adPlan.getPlanStatus(), adPlan.getStartDate(), adPlan.getEndDate())
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdPlanTable adPlanTable : plans) {
                writer.write(JSON.toJSONString(adPlanTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dumpAdPlanTable error");
        }
    }

    private void dumpAdUnitTable(String fileName) {
        List<AdUnit> adUnits = adUnitRepository.findAllByUnitStatus(CommonEnum.VALID.getStatus());
        if (CollectionUtils.isEmpty(adUnits)) return;

        List<AdUnitTable> unitTables = new ArrayList<>();
        adUnits.forEach(adUnit -> unitTables.add(
                new AdUnitTable(adUnit.getId(), adUnit.getUnitStatus(), adUnit.getPositionType(), adUnit.getPlanId())
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitTable adUnitTable : unitTables) {
                writer.write(JSON.toJSONString(adUnitTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dumpAdUnitTable error");
        }
    }

    private void dumpAdCreativeTable(String fileName) {
        List<Creative> creatives = creativeRepository.findAll();
        if (CollectionUtils.isEmpty(creatives)) return;

        List<CreativeTable> creativeTables = new ArrayList<>();
        creatives.forEach(creative -> creativeTables.add(
                new CreativeTable(creative.getId(), creative.getName(), creative.getType(), creative.getMaterialType(), creative.getHeight(), creative.getWidth(),
                        creative.getAuditStatus(), creative.getUrl())
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (CreativeTable creativeTable : creativeTables) {
                writer.write(JSON.toJSONString(creativeTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dumpCreativeTable error");
        }
    }

    private void dumpAdCreativeUnitTable(String fileName) {
        List<CreativeUnit> creativeUnits = creativeUnitRepository.findAll();
        if (CollectionUtils.isEmpty(creativeUnits)) return;

        List<AdCreativeUnitTable> adCreativeUnitTables = new ArrayList<>();
        creativeUnits.forEach(creativeUnit -> adCreativeUnitTables.add(
                new AdCreativeUnitTable(creativeUnit.getCreativeId(), creativeUnit.getUnitId())
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdCreativeUnitTable adCreativeUnitTable : adCreativeUnitTables) {
                writer.write(JSON.toJSONString(adCreativeUnitTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dumpCreativeUnitTable error");
        }
    }

    private void dumpAdUnitDistrictTable(String fileName) {
        List<AdUnitDistrict> all = adUnitDistrictRepository.findAll();
        if (CollectionUtils.isEmpty(all)) return;

        List<AdUnitDistrictTable> adUnitDistricts = new ArrayList<>();
        all.forEach(adUnitDistrict -> adUnitDistricts.add(
                new AdUnitDistrictTable(adUnitDistrict.getUnitId(), adUnitDistrict.getProvince(), adUnitDistrict.getCity())
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitDistrictTable adUnitDistrictTable : adUnitDistricts) {
                writer.write(JSON.toJSONString(adUnitDistrictTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dumpUnitDistrictTable error");
        }
    }


    private void dumpAdUnitItTable(String fileName) {
        List<AdUnitIt> all = adUnitItRepository.findAll();
        if (CollectionUtils.isEmpty(all)) return;

        List<AdUnitItTable> adUnitItTables = new ArrayList<>();
        all.forEach(adUnitIt -> adUnitItTables.add(
                new AdUnitItTable(adUnitIt.getUnitId(), adUnitIt.getItTag())
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitItTable adUnitItTable : adUnitItTables) {
                writer.write(JSON.toJSONString(adUnitItTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dumpUnitItTable error");
        }
    }

    private void dumpAdUnitKeywordTable(String fileName) {
        List<AdUnitKeyword> all = adUnitKeywordRepository.findAll();
        if (CollectionUtils.isEmpty(all)) return;

        List<AdUnitKeywordTable> adUnitKeywordTables = new ArrayList<>();
        all.forEach(adUnitKeyword -> adUnitKeywordTables.add(
                new AdUnitKeywordTable(adUnitKeyword.getUnitId(), adUnitKeyword.getKeyword())
        ));

        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (AdUnitKeywordTable adUnitKeywordTable : adUnitKeywordTables) {
                writer.write(JSON.toJSONString(adUnitKeywordTable));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            log.error("dumpUnitKeywordTable error");
        }
    }

}
