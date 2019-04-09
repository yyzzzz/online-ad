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
public class CreativeTable {

    private Long adId;

    private String name;

    private Integer type;

    private Integer materialType;

    private Integer height;

    private Integer width;

    private Integer auditStatus;

    private String adUrl;
}
