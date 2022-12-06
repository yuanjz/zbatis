package com.zbatis.spring.annotation;

import com.zbatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: yuanjz
 * @Date: 2022-08-09 14:09
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MapperScannerRegistrar.class)
public @interface MapperScan {
    String[] basePackages() default {};

    Class<? extends MapperFactoryBean> factoryBean() default MapperFactoryBean.class;
}
