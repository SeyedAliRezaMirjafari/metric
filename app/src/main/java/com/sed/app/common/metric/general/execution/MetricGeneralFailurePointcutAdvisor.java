package com.sed.app.common.metric.general.execution;

import com.sed.app.common.metric.general.annotation.MetricFailureCount;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.stereotype.Component;

@Component
public class MetricGeneralFailurePointcutAdvisor extends AbstractPointcutAdvisor {

	private static final long serialVersionUID = 5780276746956699871L;

	private final Advice advice;
	private final Pointcut pointcut;

	public MetricGeneralFailurePointcutAdvisor(MetricGeneralFailureMethodInterceptor interceptor) {
		this.advice = interceptor;
		this.pointcut = new ComposablePointcut()
				.intersection(AnnotationMatchingPointcut.forMethodAnnotation(MetricFailureCount.class));
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
