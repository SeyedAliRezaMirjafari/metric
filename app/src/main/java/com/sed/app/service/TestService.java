package com.sed.app.service;


import com.sed.app.common.metric.MetricEvents;
import com.sed.app.common.metric.MetricTags;
import com.sed.app.common.metric.general.annotation.MetricFailureCount;
import com.sed.app.common.metric.general.annotation.MetricSuccessCount;
import org.springframework.stereotype.Service;

@Service
public class TestService {

	@MetricSuccessCount(event = MetricEvents.ACTIVATION_STEP,tags = {MetricTags.REGISTERED})
	public int t(int i) {
		int x=1/i;
		return x;
	}

	@MetricFailureCount(event =  MetricEvents.ACTIVATION_STEP,tags = {MetricTags.ACTIVE})
	public int t1(int i) {
		int x=1/i;
		return x;
	}

	@MetricSuccessCount(event = MetricEvents.ACTIVATION_STEP,tags = {MetricTags.ACTIVE, MetricTags.REGISTERED})
	@MetricFailureCount(event = MetricEvents.ACTIVATION_STEP,tags = {MetricTags.ACTIVE, MetricTags.REGISTERED})
	public int t2(int i) {
		int x=1/i;
		return x;
	}


}
