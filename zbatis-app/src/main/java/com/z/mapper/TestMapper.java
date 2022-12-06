package com.z.mapper;

import com.z.entity.Test;
import com.z.zbatis.annotations.Mapper;
import com.z.zbatis.annotations.Param;

import java.util.List;

/**
 * @Author: yuanjz
 * @Date: 2022-08-11 17:42
 * @Description:
 */
@Mapper
public interface TestMapper {
    Test queryById(@Param("id") Long id);
    List<Test> queryAll();
}
