package com.ds.microservices.sport.tabletennis.report.mapper;

import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import com.ds.microservices.sport.tabletennis.report.dto.GroupDto;
import com.ds.microservices.sport.tabletennis.report.entity.Group;


@Mapper(componentModel = "spring")
public interface GroupMapper {
	
	public abstract Group groupDtoToGroup(GroupDto groupDTO, @Context CycleAvoidMappingContext context);
	
	public abstract GroupDto groupToGroupDto(Group group, @Context CycleAvoidMappingContext context);

	public abstract List<GroupDto> groupToGroupDto(List<Group> group, @Context CycleAvoidMappingContext context);
    
}
