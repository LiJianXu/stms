package com.jtdd.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义JSON过滤注解
 * @author ljx
 * CreateTime:2017年11月19日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(JSONS.class)   // 让方法支持多重 @JSONS 注解
public @interface JSON {
  Class<?> type();
  String include() default "";
  String filter() default "";
}
