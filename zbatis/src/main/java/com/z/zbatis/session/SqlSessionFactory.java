package com.z.zbatis.session;

/**
 * @Author: yuanjz
 * @Date: 2022-08-01 11:06
 * @Description:
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
