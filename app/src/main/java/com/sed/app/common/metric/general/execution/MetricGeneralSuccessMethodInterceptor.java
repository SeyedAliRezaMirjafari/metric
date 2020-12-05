package com.sed.app.common.metric.general.execution;

import com.digipay.component.metric.handler.MetricHandler;
import com.digipay.component.metric.handler.MetricHandlerFactory;
import com.digipay.component.metric.model.InputModel;
import com.digipay.component.metric.model.OutputModel;
import com.sed.app.common.metric.general.annotation.MetricSuccessCount;
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
public class MetricGeneralSuccessMethodInterceptor implements MethodInterceptor {

	private final MetricHandlerFactory metricHandlerFactory;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		MetricSuccessCount metric = invocation.getMethod().getAnnotation(MetricSuccessCount.class);
		MetricHandler metricHandler = metricHandlerFactory.getHandler(GeneralCountMetricHandler.HANDLER_NAME);
		GeneralInputMetricModel input = new GeneralInputMetricModel();
		input.setEvent(metric.event());
		input.setTags(Stream.concat(Arrays.stream(metric.tags()), Stream.of("success.true")).toArray(String[]::new));
		return proceed(invocation, metricHandler, input);
	}

	Object proceed(MethodInvocation invocation, MetricHandler metricHandler, InputModel inputModel) throws Throwable {
		try {
			Object response = invocation.proceed();
			handle(metricHandler, inputModel, metricHandler.outputModel(response));
			return response;
		} catch (Exception ex) {
			throw ex;
		}
	}

	protected void handle(MetricHandler metricHandler, InputModel input, OutputModel output) {
		try {
			metricHandler.handle(input, output);
		} catch (Exception ex) {
		}
	}

}
