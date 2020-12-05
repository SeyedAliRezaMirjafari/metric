package com.sed.app.common.metric.general.model;

import com.digipay.component.metric.model.InputModel;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GeneralInputMetricModel extends InputModel {
	String event;
	String[] tags;
}