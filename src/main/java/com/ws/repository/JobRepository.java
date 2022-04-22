package com.ws.repository;

import com.ws.repository.model.JobsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobsEntity,Long> {

}
