package com.z.zbatis.session;

import com.z.zbatis.binding.MapperProxy;
import com.z.zbatis.config.Configuration;

import java.lang.reflect.Proxy;
import java.sql.*;

/**
 * @Author: yuanjz
 * @Date: 2022-08-01 11:07
 * @Description:
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;

    private Connection connection;

    public DefaultSqlSession(Configuration configuration) {
        this.cfg = configuration;
        try {
            Class.forName(cfg.getDriver());
            this.connection = DriverManager.getConnection(cfg.getUrl(), cfg.getUsername(), cfg.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T getMapper(Class<T> mapperClass) {
        return (T) Proxy.newProxyInstance(mapperClass.getClassLoader(), new Class[]{mapperClass},
                new MapperProxy(cfg, connection));
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
