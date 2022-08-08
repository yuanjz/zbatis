package com.z.zbatis.binding;

import com.z.zbatis.annotations.Param;
import com.z.zbatis.config.Configuration;
import com.z.zbatis.executor.BaseExecutor;
import com.z.zbatis.mapping.BoundSql;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yuanjz
 * @Date: 2022-08-01 14:59
 * @Description:
 */
public class MapperProxy implements InvocationHandler {

    private Configuration cfg;
    private Connection conn;

    public MapperProxy(Configuration configuration, Connection connection) {
        this.cfg = configuration;
        this.conn = connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String key = className + "." + methodName;
        BoundSql boundSql = this.cfg.getMapper().get(key);
        if (args != null && args.length > 0) {
            Map<String, Object> params = new HashMap<>(args.length);
            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                params.put(parameters[i].getAnnotation(Param.class).value(), args[i]);
            }
            boundSql.setParams(params);
        }
        List<Object> list = new BaseExecutor().query(boundSql, conn);
        Class<?> returnType = method.getReturnType();
        if (Collection.class.isAssignableFrom(returnType) || returnType.isArray()) {
            return list;
        }
        return list.get(0);
    }
}
