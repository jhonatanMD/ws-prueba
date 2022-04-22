package com.ws.mapper;

import com.ws.model.Jobs;
import com.ws.repository.model.JobsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" )
public interface JobMapper {

    Jobs toModel(JobsEntity entity);
}
