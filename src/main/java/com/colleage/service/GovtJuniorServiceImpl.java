package com.colleage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colleage.entity.GovtJuniorEntity;
import com.colleage.exception.StudentNotFoundException;
import com.colleage.repository.GovtJuniorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GovtJuniorServiceImpl implements GovtJuniorService{

	private final GovtJuniorRepository govtJuniorRepository;

	@Transactional
	@Override
	public void createStudent(GovtJuniorEntity entity) {
		Optional<GovtJuniorEntity> existing = govtJuniorRepository.findByRollNoAndGroup(entity.getRollNo(),entity.getGroup());

		if(existing.isPresent()){
			throw new RuntimeException("Student already exists with rollNo: "+ entity.getRollNo() + " and group: " + entity.getGroup());
		}
		govtJuniorRepository.save(entity);
	}

	@Override
	public List<GovtJuniorEntity> getAllStudents() {
		return govtJuniorRepository.findAll();
	}

	@Override
	public GovtJuniorEntity getStudentById(Integer id) {
		return govtJuniorRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student Not Found With this ID::"+id));
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		GovtJuniorEntity student = govtJuniorRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student Not Found With this ID::"+id));
		govtJuniorRepository.delete(student);

	}

	@Override
	@Transactional
	public GovtJuniorEntity update(Integer id, GovtJuniorEntity entity) {
		GovtJuniorEntity student = govtJuniorRepository.findById(id).orElseThrow(() ->  new StudentNotFoundException("Student Not Found With this ID::"+id));
		student.setName(entity.getName());
		student.setCampus(entity.getCampus());
		student.setGroup(entity.getGroup());
		student.setRollNo(entity.getRollNo());
		return govtJuniorRepository.save(student);
	}

}
