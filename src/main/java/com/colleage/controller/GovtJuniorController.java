package com.colleage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.colleage.entity.GovtJuniorEntity;
import com.colleage.service.GovtJuniorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/colleage")
@RequiredArgsConstructor
@Slf4j
public class GovtJuniorController {

    private final GovtJuniorService govtJuniorService;

    /**
     * @param entity
     * @return
     * @author sreekanth.g
     */
    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@Valid @RequestBody GovtJuniorEntity entity) {
        log.info("START :: {} :: createStudent()", this.getClass().getSimpleName());
        govtJuniorService.createStudent(entity);
        log.info("END :: {} :: createStudent()", this.getClass().getSimpleName());
        return new ResponseEntity<String>("Student created successfully.", HttpStatus.CREATED);
    }

    /**
     * @return
     * @author sreekanth.g
     */
    @GetMapping("/getAllStudents")
    public ResponseEntity<List<GovtJuniorEntity>> getAllStudents() {
        log.info("START::{}::getAllStudents()", this.getClass().getSimpleName());
        List<GovtJuniorEntity> allStudensts = govtJuniorService.getAllStudents();
        log.info("END::{}::getAllStudents()", this.getClass().getSimpleName());
        return new ResponseEntity<List<GovtJuniorEntity>>(allStudensts, HttpStatus.OK);
    }

    /**
     * @param id
     * @return
     * @author sreekanth.g
     */
    @GetMapping("/ById/{id}")
    public ResponseEntity<GovtJuniorEntity> getStudentById(@PathVariable Integer id) {
        log.info("START::{}::getStudentById()::id={}", this.getClass().getSimpleName(), id);
        GovtJuniorEntity student = govtJuniorService.getStudentById(id);
        log.info("END::{}::getStudentById()", this.getClass().getSimpleName());
        return new ResponseEntity<GovtJuniorEntity>(student, HttpStatus.OK);
    }

    /**
     * @param id
     * @return
     * @author sreekanth.g
     */
    @GetMapping("/ById")
    public ResponseEntity<GovtJuniorEntity> getByIdUsingRequestParam(@RequestParam Integer id) {
        log.info("START::{}::getByIdUsingRequestParam()::id={}", this.getClass().getSimpleName(), id);
        GovtJuniorEntity student = govtJuniorService.getStudentById(id);
        log.info("END::{}::getByIdUsingRequestParam()", this.getClass().getSimpleName());
        return new ResponseEntity<GovtJuniorEntity>(student, HttpStatus.OK);
    }


    /**
     * @param id
     * @return
     * @author sreekanth.g
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        log.info("START::{}::delete()", this.getClass().getSimpleName());
        govtJuniorService.delete(id);
        log.info("END::{}::delete()", this.getClass().getSimpleName());
        return new ResponseEntity<String>("Student Deleted Successfully...", HttpStatus.OK);
    }

    /**
     * @param id
     * @param entity
     * @return
     * @author sreekanth.g
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<GovtJuniorEntity> update(@PathVariable Integer id, @Valid @RequestBody GovtJuniorEntity entity) {
        log.info("START::{}::update()::id={}", this.getClass().getSimpleName(), id);
        GovtJuniorEntity student = govtJuniorService.update(id, entity);
        log.info("END::{}::update()", this.getClass().getSimpleName());
        return ResponseEntity.ok(student);
    }

    /**
     * @author sreekanth.g
     * @param s
     * @return
     *
     */
    @GetMapping("/hello")
    public ResponseEntity<String> getMessage(String s) {
        return new ResponseEntity<>("Hello Sreekanth", HttpStatus.OK);
    }

    @GetMapping("/greet")
    public String greet(){
        return "Hi good morning";
    }

	@GetMapping("/show")
	public String show(){
		return "show ";
	}

}
