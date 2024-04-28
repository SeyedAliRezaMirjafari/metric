package com.sed.app.common.metric.general.Interceptor;

import com.sed.app.common.metric.general.annotation.Metric;
import com.sed.app.common.metric.general.handler.base.MetricHandler;
import com.sed.app.common.metric.general.handler.base.MetricHandlerFactory;
import com.sed.app.common.metric.general.model.base.InputModel;
import com.sed.app.common.metric.general.model.base.OutputModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
@RequiredArgsConstructor
public class MetricMethodInterceptor implements MethodInterceptor {

	private final MetricHandlerFactory metricHandlerFactory;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Metric metric = invocation.getMethod().getAnnotation(Metric.class);
		MetricHandler metricHandler = metricHandlerFactory.getHandler(metric.handler());
		return proceed(invocation, metricHandler, getInputModel(metricHandler, invocation));
	}

	private InputModel getInputModel(MetricHandler metricHandler, MethodInvocation invocation) {
		try {
			return metricHandler.inputModel(invocation);
		} catch (Exception ex) {
			return new InputModel();
		}
	}

	Object proceed(MethodInvocation invocation, MetricHandler metricHandler, InputModel inputModel) throws Throwable {
		try {
			Object response = invocation.proceed();
			handle(metricHandler, inputModel, metricHandler.outputModel(response));
			return response;
		} catch (Exception ex) {
			handleException(metricHandler, inputModel, ex);
			throw ex;
		}
	}

	protected void handle(MetricHandler metricHandler, InputModel input, OutputModel output) {
		try {
			metricHandler.handle(input, output);
		} catch (Exception ex) {
		}
	}

	protected void handleException(MetricHandler metricHandler, InputModel input, Exception e) {
		try {
			metricHandler.handleException(input, e);
		} catch (Exception ex) {
		}
	}

}
