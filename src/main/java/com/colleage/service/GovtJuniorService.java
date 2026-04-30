package com.colleage.service;

import java.util.List;

import com.colleage.entity.GovtJuniorEntity;

public interface GovtJuniorService {

	public void createStudent(GovtJuniorEntity entity);
	public List<GovtJuniorEntity> getAllStudents();
	public GovtJuniorEntity getStudentById(Integer id);
	public void delete(Integer id);
	public GovtJuniorEntity update(Integer id, GovtJuniorEntity entity);

}
