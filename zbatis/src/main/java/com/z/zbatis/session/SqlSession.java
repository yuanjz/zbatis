package com.z.zbatis.session;

/**
 * @Author: yuanjz
 * @Date: 2022-08-01 11:07
 * @Description:
 */
public interface SqlSession {

    <T> T getMapper(Class<T> mapperClass);

    void close();
}
