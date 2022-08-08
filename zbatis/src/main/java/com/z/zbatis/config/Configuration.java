package com.z.zbatis.config;

import com.z.zbatis.mapping.BoundSql;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yuanjz
 * @Date: 2022-07-29 16:33
 * @Description:
 */
@Data
public class Configuration {

    private String driver;

    private String url;

    private String username;

    private String password;

    private Map<String, BoundSql> mapper = new HashMap<>();
}
