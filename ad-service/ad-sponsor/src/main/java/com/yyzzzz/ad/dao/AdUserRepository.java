package com.yyzzzz.ad.dao;

import com.yyzzzz.ad.domain.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yyzzzz on 2019/3/16.
 */
public interface AdUserRepository extends JpaRepository<AdUser, Long> {

    AdUser findByUsername(String name);
}
