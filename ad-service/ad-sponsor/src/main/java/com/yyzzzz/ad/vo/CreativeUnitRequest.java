package com.yyzzzz.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by yyzzzz on 2019/3/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreativeUnitRequest {

    private List<CreativeUnitItem> items;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreativeUnitItem {
        private Long unitId;
        private Long creativeId;
    }
}
