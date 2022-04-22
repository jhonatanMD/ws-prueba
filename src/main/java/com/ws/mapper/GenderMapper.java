package com.ws.mapper;

import com.ws.model.Genders;
import com.ws.model.Jobs;
import com.ws.repository.model.GendersEntity;
import com.ws.repository.model.JobsEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring" )
public interface GenderMapper {

    Genders toModel(GendersEntity entity);
}
