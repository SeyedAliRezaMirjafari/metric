package com.sed.app.common.metric.general.execution;

import com.digipay.component.metric.handler.MetricHandler;
import com.digipay.component.metric.handler.MetricHandlerFactory;
import com.digipay.component.metric.model.InputModel;
import com.sed.app.common.metric.general.annotation.MetricFailureCount;
import com.sed.app.common.metric.general.handler.GeneralCountMetricHandler;
import com.sed.app.common.metric.general.model.GeneralInputMetricModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Arrays;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class MetricGeneralFailureMethodInterceptor implements MethodInterceptor {

	private final MetricHandlerFactory metricHandlerFactory;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		MetricFailureCount metric = invocation.getMethod().getAnnotation(MetricFailureCount.class);
		MetricHandler metricHandler = metricHandlerFactory.getHandler(GeneralCountMetricHandler.HANDLER_NAME);
		GeneralInputMetricModel input = new GeneralInputMetricModel();
		input.setEvent(metric.event());
		input.setTags(Stream.concat(Arrays.stream(metric.tags()), Stream.of("success.false")).toArray(String[]::new));
		return proceed(invocation, metricHandler, input);
	}

	Object proceed(MethodInvocation invocation, MetricHandler metricHandler, InputModel inputModel) throws Throwable {
		try {
			return invocation.proceed();
		} catch (Exception ex) {
			handleException(metricHandler, inputModel, ex);
			throw ex;
		}
	}


	protected void handleException(MetricHandler metricHandler, InputModel input, Exception e) {
		try {
			metricHandler.handleException(input, e);
		} catch (Exception ex) {
		}
	}

}
