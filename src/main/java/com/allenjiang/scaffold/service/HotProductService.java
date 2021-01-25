package com.allenjiang.scaffold.service;

import com.allenjiang.scaffold.pojo.HotClassInfo;

import java.util.List;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.allenjiang.scaffold.service
 * @date:2021-01-14
 */
public interface HotProductService {

    List<HotClassInfo> queryAllHotClassInfo();
}
