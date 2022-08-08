package com.z.zbatis.executor;

import com.z.zbatis.mapping.BoundSql;
import com.z.zbatis.mapping.ResultMapping;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: yuanjz
 * @Date: 2022-08-01 15:05
 * @Description:
 */
public class BaseExecutor {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");

    public <E> List<E> query(BoundSql boundSql, Connection conn) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(this.parseSql(boundSql));
            rs = statement.executeQuery();
            Class<?> resultTypeClass = boundSql.getResultMap().getResultType();
            List<ResultMapping> resultMappings = boundSql.getResultMap().getResultMappings();
            List<E> list = new ArrayList<>();
            while (rs.next()) {
                E result = (E) resultTypeClass.newInstance();
                ResultSetMetaData metaData = rs.getMetaData();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnName(i + 1);
                    String columnValue = rs.getString(columnName);
                    String propertyName = null;
                    for (ResultMapping resultMapping : resultMappings) {
                        if (resultMapping.getColumn().equals(columnName)) {
                            propertyName = resultMapping.getProperty();
                            break;
                        }
                    }
                    if (propertyName == null) {
                        throw new RuntimeException("Cannot match property name.");
                    }
                    Field field = resultTypeClass.getDeclaredField(propertyName);
                    field.setAccessible(true);
                    if ("java.lang.Long".equals(field.getType().getName())) {
                        field.set(result, Long.valueOf(columnValue));
                    } else if ("java.lang.Double".equals(field.getType().getName())) {
                        field.set(result, Double.valueOf(columnValue));
                    } else if ("java.lang.Integer".equals(field.getType().getName())) {
                        field.set(result, Integer.valueOf(columnValue));
                    } else if ("java.util.Date".equals(field.getType().getName())) {
                        field.set(result, sdf.parse(columnValue));
                    } else {
                        field.set(result, columnValue);
                    }
                }
                list.add(result);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String parseSql(BoundSql boundSql) {
        Map<String, Object> params = boundSql.getParams();
        String sql = boundSql.getSql();
        if (params == null) {
            return sql;
        }
        for (String paramKey : params.keySet()) {
            sql = sql.replace("#{" + paramKey + "}", params.get(paramKey).toString());
        }
        return sql;
    }
}
