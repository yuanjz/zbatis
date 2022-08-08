package com.z.zbatis.mapping;

import lombok.Data;

import java.util.Map;

/**
 * @Author: yuanjz
 * @Date: 2022-08-01 15:33
 * @Description:
 */
@Data
public class BoundSql {

    private String sql;

    private Map<String, Object> params;

    private ResultMap resultMap;
}
