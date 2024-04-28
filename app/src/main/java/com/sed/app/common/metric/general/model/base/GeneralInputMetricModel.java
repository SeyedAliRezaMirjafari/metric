package com.sed.app.common.metric.general.model.base;
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