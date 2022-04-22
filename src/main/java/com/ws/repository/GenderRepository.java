package com.ws.repository;

import com.ws.repository.model.GendersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<GendersEntity,Long> {
}
