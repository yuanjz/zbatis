package com.z.zbatis.mapping;

import lombok.Data;

import java.util.List;

/**
 * @Author: yuanjz
 * @Date: 2022-08-04 16:30
 * @Description:
 */
@Data
public class ResultMap {
    private Class<?> resultType;
    private List<ResultMapping> resultMappings;
}
