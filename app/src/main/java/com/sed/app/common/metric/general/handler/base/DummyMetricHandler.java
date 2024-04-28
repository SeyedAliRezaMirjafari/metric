package com.sed.app.common.metric.general.handler.base;

import com.sed.app.common.metric.general.model.base.InputModel;
import com.sed.app.common.metric.general.model.base.OutputModel;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;


@Component
@Slf4j
public class DummyMetricHandler implements MetricHandler {

	@Override
	public String name() {
		return "dummy";
	}

	@Override
	public void handle(InputModel inputModel, OutputModel outputModel) {
	}

	@Override
	public void handleException(InputModel inputModel, Exception ex) {
	}

}
