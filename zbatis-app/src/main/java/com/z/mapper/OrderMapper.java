package com.z.mapper;

import com.z.entity.Order;
import com.z.zbatis.annotations.Param;

/**
 * @Author: yuanjz
 * @Date: 2022-08-05 14:21
 * @Description:
 */
public interface OrderMapper {
    Order queryById(@Param("orderId") Long orderId);
}
