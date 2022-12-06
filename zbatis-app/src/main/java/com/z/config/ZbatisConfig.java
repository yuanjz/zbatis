//package com.z.config;
//
//import com.z.zbatis.session.SqlSessionFactory;
//import com.zbatis.spring.SqlSessionFactoryBean;
//import com.zbatis.spring.annotation.MapperScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Author: yuanjz
// * @Date: 2022-08-09 16:18
// * @Description:
// */
//@Configuration
//@MapperScan(basePackages = {"com.z.mapper"})
//public class ZbatisConfig {
//
//    @Bean("sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setConfigLocation("zbatis-Config.xml");
//        return sqlSessionFactoryBean.getObject();
//    }
//}
