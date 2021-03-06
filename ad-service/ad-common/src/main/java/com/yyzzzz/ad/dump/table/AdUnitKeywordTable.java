package com.yyzzzz.ad.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yyzzzz on 2019/4/9.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitKeywordTable {

    private Long unitId;

    private String keyword;
}
