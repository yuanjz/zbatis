package com.z.mapper;

import com.z.entity.Order;
import com.z.zbatis.annotations.Mapper;
import com.z.zbatis.annotations.Param;

/**
 * @Author: yuanjz
 * @Date: 2022-08-05 14:21
 * @Description:
 */
@Mapper
public interface OrderMapper {
    Order queryById(@Param("orderId") Long orderId);
}
