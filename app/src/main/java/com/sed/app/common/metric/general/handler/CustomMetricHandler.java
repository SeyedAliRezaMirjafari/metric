package com.sed.app.common.metric.general.handler;

import com.sed.app.common.metric.MetricEvents;
import com.sed.app.common.metric.MetricTags;
import com.sed.app.common.metric.general.handler.base.MetricHandler;
import com.sed.app.common.metric.general.model.base.InputModel;
import com.sed.app.common.metric.general.model.base.OutputModel;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class CustomMetricHandler implements MetricHandler {

    public static final String HANDLER_NAME = "time-inquiry";

    private final MeterRegistry registry;
    @Override
    public String name() {
        return this.HANDLER_NAME;
    }

    @Override
    public void handle(InputModel inputModel, OutputModel outputModel) {
        List<Tag> tags = Collections.singletonList(Tag.of(MetricTags.STATUS, "success"));
        registry.counter(MetricEvents.INQUIRY, tags).increment();
        registry.timer(MetricEvents.INQUIRY, tags)
                .record(System.currentTimeMillis() - inputModel.getTime(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void handleException(InputModel inputModel, Exception ex) {

    }
}
