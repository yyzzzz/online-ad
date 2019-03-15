package com.yyzzzz.ad.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yyzzzz on 2019/3/14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_creative")
public class Creative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // 大类型 如图片
    @Column(name = "type", nullable = false)
    private Integer type;

    //细分类型 如img png bmp
    @Column(name = "material_type", nullable = false)
    private Integer materialType;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "width", nullable = false)
    private Integer width;

    @Column(name = "size", nullable = false)
    private Long size;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    /** 审核状态 */
    @Column(name = "audit_status", nullable = false)
    private Integer auditStatus;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "update_time", nullable = false)
    private Date updateTime;
}
