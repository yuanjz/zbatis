package com.z.service;

import com.z.entity.Cust;

import java.util.List;

/**
 * @Author: yuanjz
 * @Date: 2022-08-09 16:05
 * @Description:
 */
public interface CustService {

    Cust queryById(Long custId);

    List<Cust> queryAll();
}
