package com.sed.app.common.metric.general.Interceptor;

import com.sed.app.common.metric.general.annotation.Metric;
import org.aopalliance.aop.Advice;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.stereotype.Component;



@Component
public class MetricPointcutAdvisor extends AbstractPointcutAdvisor {

	private static final long serialVersionUID = 5780276746956699871L;

	private final Advice advice;

	private final Pointcut pointcut;

	public MetricPointcutAdvisor(MetricMethodInterceptor interceptor) {
		this.advice = interceptor;
		this.pointcut = new ComposablePointcut()
				.intersection(AnnotationMatchingPointcut.forMethodAnnotation(Metric.class));
	}

	@Override
	public Pointcut getPointcut() {
		return pointcut;
	}

	@Override
	public Advice getAdvice() {
		return advice;
	}

}
