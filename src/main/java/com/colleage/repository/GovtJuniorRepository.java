package com.colleage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colleage.entity.GovtJuniorEntity;

@Repository
public interface GovtJuniorRepository  extends JpaRepository<GovtJuniorEntity, Integer>{
	
	Optional<GovtJuniorEntity> findByRollNoAndGroup(String rollNo, String group);
}
