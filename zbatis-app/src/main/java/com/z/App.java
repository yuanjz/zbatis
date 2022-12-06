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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * @Author: yuanjz
 * @Date: 2022-07-29 16:32
 * @Description:
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    public static void zbatis() throws Exception {
        // 加载配置文件
        Configuration config = Resources.loadConfig("zbatis-Config.xml");

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
