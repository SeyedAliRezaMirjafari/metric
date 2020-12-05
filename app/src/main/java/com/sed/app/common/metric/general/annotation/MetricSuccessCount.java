package com.sed.app.common.metric.general.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MetricSuccessCount {
	String event();
	String[] tags() default {};
}

