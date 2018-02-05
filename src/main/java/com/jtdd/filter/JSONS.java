package com.jtdd.filter;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * 实现过滤多个JSON注解
 * @author ljx
 * CreateTime:2017年11月19日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSONS {
	JSON[] value();
}
