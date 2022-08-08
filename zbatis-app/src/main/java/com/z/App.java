package com.z;

import com.z.entity.Cust;
import com.z.entity.Order;
import com.z.mapper.CustMapper;
import com.z.mapper.OrderMapper;
import com.z.zbatis.config.Configuration;
import com.z.zbatis.session.DefaultSqlSessionFactory;
import com.z.zbatis.session.SqlSession;
import com.z.zbatis.session.SqlSessionFactory;
import com.z.zbatis.utils.Resources;

import java.util.List;

/**
 * @Author: yuanjz
 * @Date: 2022-07-29 16:32
 * @Description:
 */
public class App {

    public static void main(String[] args) throws Exception {
        // 加载配置文件
        Configuration config = Resources.loadConfig("Mybatis-Config.xml");

        // open sqlSession
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(config);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取mapper
        CustMapper custMapper = sqlSession.getMapper(CustMapper.class);
        List<Cust> list = custMapper.queryAll();
        System.out.println(list);

        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        Order order = orderMapper.queryById(20190626000001L);
        System.out.println(order);

        // close sqlSession
        sqlSession.close();
    }
}
