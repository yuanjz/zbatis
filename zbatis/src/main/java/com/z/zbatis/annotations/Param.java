package com.z.zbatis.annotations;

import java.lang.annotation.*;

/**
 * @Author: yuanjz
 * @Date: 2022-08-03 17:15
 * @Description:
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Param {
    String value();
}
