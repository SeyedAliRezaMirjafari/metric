package com.sed.app.common.metric.general.handler.base;

import com.sed.app.common.metric.general.model.base.InputModel;
import com.sed.app.common.metric.general.model.base.OutputModel;
import org.aopalliance.intercept.MethodInvocation;

public interface MetricHandler {

	String name();

	void handle(InputModel inputModel, OutputModel outputModel);

	void handleException(InputModel inputModel, Exception ex);

	default InputModel inputModel(MethodInvocation invocation) {
		InputModel model = new InputModel();
		model.setTime(System.currentTimeMillis());
		return model;
	}

	default OutputModel outputModel(Object response) {
		OutputModel model = new OutputModel();
		model.setTime(System.currentTimeMillis());
		return model;
	}

}
