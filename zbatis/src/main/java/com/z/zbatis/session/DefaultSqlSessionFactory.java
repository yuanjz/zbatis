package com.z.zbatis.session;

import com.z.zbatis.config.Configuration;

/**
 * @Author: yuanjz
 * @Date: 2022-08-01 11:06
 * @Description:
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.cfg = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
