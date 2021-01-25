package com.allenjiang.scaffold.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 热卖分组
 *
 * @author jxw
 */
@Data
public class HotClassInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -22515335488L;

    private int id;
    private String className;// 商品分类id
    private String jsonName;// 商品分类名称
    private int adminId;// 创建人id
    private String adminName;// 创建人
    private String createTime;// 创建时间
    private int updateAdminId;// 更新人id
    private int updateAdminName;// 更新人id
    private String updateTime;// 更新时间
    private int startNum;
    private int limitNum;


    private Integer hotClassId;
    private int webSite = -1;

    private int freeFlag = 1;


}
