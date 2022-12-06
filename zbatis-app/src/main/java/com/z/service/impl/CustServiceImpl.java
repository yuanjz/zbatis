package com.z.service.impl;

import com.z.entity.Cust;
import com.z.mapper.CustMapper;
import com.z.service.CustService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: yuanjz
 * @Date: 2022-08-09 16:06
 * @Description:
 */
@Service
public class CustServiceImpl implements CustService {

    @Resource
    private CustMapper custMapper;

    @Override
    public Cust queryById(Long custId) {
        return custMapper.queryById(custId);
    }

    @Override
    public List<Cust> queryAll() {
        return custMapper.queryAll();
    }
}
