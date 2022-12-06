package com.z.zbatis.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author: yuanjz
 * @Date: 2022-08-11 15:01
 * @Description:
 */
@Documented
@Inherited
@Retention(RUNTIME)
@Target({ TYPE })
public @interface Mapper {
}
