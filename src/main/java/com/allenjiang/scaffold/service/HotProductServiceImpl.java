package com.allenjiang.scaffold.service;

import com.allenjiang.scaffold.mapper.HotProductMapper;
import com.allenjiang.scaffold.pojo.HotClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.allenjiang.scaffold.service
 * @date:2021-01-14
 */
@Service
public class HotProductServiceImpl implements HotProductService {

    @Autowired
    private HotProductMapper productMapper;

    @Override
    public List<HotClassInfo> queryAllHotClassInfo() {
        return productMapper.queryAllHotClassInfo();
    }
}
