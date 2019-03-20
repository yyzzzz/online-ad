package com.yyzzzz.ad.client;

import com.yyzzzz.ad.client.vo.AdPLan;
import com.yyzzzz.ad.client.vo.AdPlanGetRequest;
import com.yyzzzz.ad.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yyzzzz on 2019/3/20.
 */
@Component
public class SponsorClientHystrix  implements SponsorClient{


    @Override
    public CommonResponse<List<AdPLan>> getAdPlans(AdPlanGetRequest request) {
        return new CommonResponse<>(-1, "eureka-client-ad-sponsor error");
    }
}
