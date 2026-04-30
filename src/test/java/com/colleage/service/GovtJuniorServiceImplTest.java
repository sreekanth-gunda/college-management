package com.colleage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.colleage.entity.GovtJuniorEntity;
import com.colleage.exception.StudentNotFoundException;
import com.colleage.repository.GovtJuniorRepository;

@ExtendWith(MockitoExtension.class)
class GovtJuniorServiceImplTest {

    @Mock
    private GovtJuniorRepository repository;

    @InjectMocks
    private GovtJuniorServiceImpl service;

    // ✅ CREATE SUCCESS
    @Test
    void createStudent_success() {
        GovtJuniorEntity entity = new GovtJuniorEntity();
        entity.setRollNo("180");
        entity.setGroup("MPC");

        when(repository.findByRollNoAndGroup("180", "MPC"))
                .thenReturn(Optional.empty());

        service.createStudent(entity);

        verify(repository, times(1)).save(entity);
    }

    // ❌ CREATE DUPLICATE
    @Test
    void createStudent_duplicate() {
        GovtJuniorEntity entity = new GovtJuniorEntity();
        entity.setRollNo("180");
        entity.setGroup("MPC");

        when(repository.findByRollNoAndGroup("180", "MPC"))
                .thenReturn(Optional.of(new GovtJuniorEntity()));

        assertThrows(RuntimeException.class, () -> {
            service.createStudent(entity);
        });
    }

    // ✅ GET ALL
    @Test
    void getAllStudents() {
        when(repository.findAll()).thenReturn(List.of(new GovtJuniorEntity()));

        List<GovtJuniorEntity> result = service.getAllStudents();

        assertFalse(result.isEmpty());
    }

    // ✅ GET BY ID SUCCESS
    @Test
    void getStudentById_success() {
        GovtJuniorEntity entity = new GovtJuniorEntity();

        when(repository.findById(1)).thenReturn(Optional.of(entity));

        GovtJuniorEntity result = service.getStudentById(1);

        assertNotNull(result);
    }

    // ❌ GET BY ID NOT FOUND
    @Test
    void getStudentById_notFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> {
            service.getStudentById(1);
        });
    }

    // ✅ DELETE SUCCESS
    @Test
    void delete_success() {
        GovtJuniorEntity entity = new GovtJuniorEntity();

        when(repository.findById(1)).thenReturn(Optional.of(entity));

        service.delete(1);

        verify(repository).delete(entity);
    }

    // ❌ DELETE NOT FOUND
    @Test
    void delete_notFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> {
            service.delete(1);
        });
    }

    // ✅ UPDATE SUCCESS
    @Test
    void update_success() {
        GovtJuniorEntity existing = new GovtJuniorEntity();
        GovtJuniorEntity updated = new GovtJuniorEntity();

        updated.setName("Sree");

        when(repository.findById(1)).thenReturn(Optional.of(existing));
        when(repository.save(any())).thenReturn(existing);

        GovtJuniorEntity result = service.update(1, updated);

        assertEquals("Sree", result.getName());
    }

    // ❌ UPDATE NOT FOUND
    @Test
    void update_notFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> {
            service.update(1, new GovtJuniorEntity());
        });
    }
}