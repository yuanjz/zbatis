package com.z.controller;

import com.z.entity.Cust;
import com.z.entity.Order;
import com.z.entity.Test;
import com.z.mapper.OrderMapper;
import com.z.mapper.TestMapper;
import com.z.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: yuanjz
 * @Date: 2022-08-09 16:08
 * @Description:
 */
@RestController
@RequestMapping(value = "/custManage")
public class CustController {

    @Autowired
    private CustService custService;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private TestMapper testMapper;

    @PostMapping(value = "queryCust")
    public Cust queryCust(@RequestParam("custId") Long custId) {
        return custService.queryById(custId);
    }

    @PostMapping(value = "queryAll")
    public List<Cust> queryCust() {
        Order order = orderMapper.queryById(20190626000001L);
        System.out.println(order);

        Test test = testMapper.queryById(1L);
        System.out.println(test);
        return custService.queryAll();
    }
}
