package com.sed.app.common.metric.general.handler.base;

import com.sed.app.common.metric.general.model.base.GeneralInputMetricModel;
import com.sed.app.common.metric.general.model.base.InputModel;
import com.sed.app.common.metric.general.model.base.OutputModel;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class GeneralCountMetricHandler implements MetricHandler {

	private final MeterRegistry registry;
	public static final String HANDLER_NAME = "GENERAL_INCREMENT_METRIC";

	@Override
	public String name() {
		return HANDLER_NAME;
	}


	@Override
	public void handle(InputModel inputModel, OutputModel outputModel) {
		GeneralInputMetricModel input= (GeneralInputMetricModel) inputModel;
		System.out.println(input.toString());
		registry.counter(input.getEvent(),input.getTags()).increment();
	}

	@Override
	public void handleException(InputModel inputModel, Exception ex) {
		GeneralInputMetricModel input= (GeneralInputMetricModel) inputModel;
		System.out.println(input.toString());
		registry.counter(input.getEvent(),input.getTags()).increment();
	}


}
