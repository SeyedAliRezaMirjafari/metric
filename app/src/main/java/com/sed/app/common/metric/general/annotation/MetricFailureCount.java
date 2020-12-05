package com.sed.app.common.metric.general.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MetricFailureCount {
	String event();
	String[] tags() default {};
}
