package com.allenjiang.scaffold.mapper;

import com.allenjiang.scaffold.pojo.HotClassInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.allenjiang.scaffold.mapper
 * @date:2021-01-14
 */
@Mapper
@Component
public interface HotProductMapper {


    @Select("select a.id,a.class_name as className,a.json_name as jsonName from hot_class_info a")
    List<HotClassInfo> queryAllHotClassInfo();
}
