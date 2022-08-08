package com.z.mapper;

import com.z.entity.Cust;
import com.z.zbatis.annotations.Param;

import java.util.List;

/**
 * @Author: yuanjz
 * @Date: 2022-08-01 13:56
 * @Description:
 */
public interface CustMapper {

    Cust queryById(@Param("custId") Long custId);

    List<Cust> queryAll();
}
