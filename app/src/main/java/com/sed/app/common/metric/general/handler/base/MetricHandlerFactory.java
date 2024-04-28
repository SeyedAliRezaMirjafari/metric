package com.sed.app.common.metric.general.handler.base;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


import org.springframework.stereotype.Component;

@Component
public class MetricHandlerFactory {

	private final Map<String, MetricHandler> handlers;

	public MetricHandlerFactory(List<MetricHandler> handlers) {
		this.handlers = handlers.stream()
				.collect(Collectors.toMap(MetricHandler::name, handler -> handler));
	}

	public MetricHandler getHandler(String handlerName) {
		MetricHandler handler = this.handlers.get(handlerName);
		if (Objects.isNull(handler)) {
			return this.handlers.get("dummy");
		}
		return handler;
	}

}
