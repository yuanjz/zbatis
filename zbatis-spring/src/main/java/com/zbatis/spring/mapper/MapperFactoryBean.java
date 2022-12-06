package com.zbatis.spring.mapper;

import com.z.zbatis.session.SqlSession;
import com.z.zbatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: yuanjz
 * @Date: 2022-08-09 14:41
 * @Description:
 */
public class MapperFactoryBean<T> implements FactoryBean<T> {

    private SqlSession sqlSession;

    private Class<T> mapperInterface;

    public MapperFactoryBean() {}

    public MapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        return sqlSession.getMapper(mapperInterface);
    }

    @Override
    public Class<T> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSession = sqlSessionFactory.openSession();
    }

    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }
}
