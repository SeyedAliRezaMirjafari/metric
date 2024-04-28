package com.sed.app;



import com.sed.app.common.metric.general.Interceptor.*;
import com.sed.app.common.metric.general.handler.base.MetricHandlerFactory;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;


@SpringBootApplication
@RequiredArgsConstructor
@Configuration
public class MetricApplication {
	private final Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(MetricApplication.class, args);
	}

	@Bean
	public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
		return registry -> registry.config().commonTags("application",
				environment.getRequiredProperty("spring.application.name"));
	}

	@Bean
	public MetricGeneralSuccessMethodInterceptor methodInterceptor1(@Lazy MetricHandlerFactory metricHandlerFactory) {
		return new MetricGeneralSuccessMethodInterceptor(metricHandlerFactory);
	}

	@Bean
	public MetricGeneralFailureMethodInterceptor methodInterceptor2(@Lazy MetricHandlerFactory metricHandlerFactory) {
		return new MetricGeneralFailureMethodInterceptor(metricHandlerFactory);
	}
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
	public MetricMethodInterceptor methodInterceptor3(@Lazy MetricHandlerFactory metricHandlerFactory) {
		return new MetricMethodInterceptor(metricHandlerFactory);
	}

}
