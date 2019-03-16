package com.yyzzzz.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by yyzzzz on 2019/3/16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlanCriteria {

    private Long userId;
    private List<Long> ids;

    public boolean validate(){
        return userId != null && !CollectionUtils.isEmpty(ids);
    }
}
