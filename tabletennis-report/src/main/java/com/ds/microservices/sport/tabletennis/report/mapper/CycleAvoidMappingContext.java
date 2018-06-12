package com.ds.microservices.sport.tabletennis.report.mapper;

import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;

import java.util.IdentityHashMap;
import java.util.Map;

public class CycleAvoidMappingContext {
	private Map<Object, Object> PROCESSED = new IdentityHashMap<>();

	@BeforeMapping
	public <T> T getMappedInstance(Object source) {
		return (T) PROCESSED.get(source);
	}

	@BeforeMapping
	public void storeMappedInstance(Object source, @MappingTarget Object target) {
		PROCESSED.put(source, target);
	}
}
