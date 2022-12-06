package com.zbatis.spring;

import com.z.zbatis.config.Configuration;
import com.z.zbatis.session.DefaultSqlSessionFactory;
import com.z.zbatis.session.SqlSessionFactory;
import com.z.zbatis.utils.Resources;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @Author: yuanjz
 * @Date: 2022-08-09 15:29
 * @Description:
 */
public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>, InitializingBean {

    private String configLocation;

    private SqlSessionFactory sqlSessionFactory;

    @Override
    public SqlSessionFactory getObject() throws Exception {
        if (this.sqlSessionFactory == null) {
            afterPropertiesSet();
        }
        return this.sqlSessionFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return this.sqlSessionFactory != null ? this.sqlSessionFactory.getClass() : SqlSessionFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.configLocation, "");
        this.sqlSessionFactory = buildSqlSessionFactory();
    }

    protected SqlSessionFactory buildSqlSessionFactory() throws Exception {
        Configuration configuration = Resources.loadConfig(this.configLocation);
        return new DefaultSqlSessionFactory(configuration);
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }
}
