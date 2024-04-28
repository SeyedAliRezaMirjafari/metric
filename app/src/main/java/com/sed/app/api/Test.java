package com.sed.app.api;

import com.sed.app.common.metric.general.handler.base.MetricHandlerFactory;
import com.sed.app.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Test {

	MetricHandlerFactory metricHandlerFactory;
	TestService testService;

	@GetMapping(path = "/test/{i}")
	public int test(@PathVariable("i") int i) {
		try {
			return testService.t(i);
		} catch (Exception e) {
			return 404;
		}

	}

	@GetMapping(path = "/test1/{i}")
	public int test1(@PathVariable("i") int i) {
		try {
			return testService.t1(i);
		} catch (Exception e) {
			return 404;
		}
	}

	@GetMapping(path = "/test2/{i}")
	public int test2(@PathVariable("i") int i) {
		try {
			return testService.t2(i);
		} catch (Exception e) {
			return 404;
		}
	}


}
