package com.sed.app;



import com.digipay.component.metric.handler.MetricHandlerFactory;
import com.sed.app.common.metric.general.execution.MetricGeneralFailureMethodInterceptor;
import com.sed.app.common.metric.general.execution.MetricGeneralSuccessMethodInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


@SpringBootApplication
@RequiredArgsConstructor
@Configuration
public class MetricApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricApplication.class, args);
	}
	@Bean
	public MetricGeneralSuccessMethodInterceptor methodInterceptor1(@Lazy MetricHandlerFactory metricHandlerFactory) {
		return new MetricGeneralSuccessMethodInterceptor(metricHandlerFactory);
	}

	@Bean
	public MetricGeneralFailureMethodInterceptor methodInterceptor2(@Lazy MetricHandlerFactory metricHandlerFactory) {
		return new MetricGeneralFailureMethodInterceptor(metricHandlerFactory);
	}


}
