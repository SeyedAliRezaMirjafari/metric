package com.sed.app.common.metric.general.Interceptor;

import com.sed.app.common.metric.general.annotation.MetricSuccessCount;
import com.sed.app.common.metric.general.handler.base.GeneralCountMetricHandler;
import com.sed.app.common.metric.general.handler.base.MetricHandler;
import com.sed.app.common.metric.general.handler.base.MetricHandlerFactory;
import com.sed.app.common.metric.general.model.base.GeneralInputMetricModel;
import com.sed.app.common.metric.general.model.base.InputModel;
import com.sed.app.common.metric.general.model.base.OutputModel;
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
