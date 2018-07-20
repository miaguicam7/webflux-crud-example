package com.example.webfluxexample.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webfluxexample.DAO.EmployeeRepository;
import com.example.webfluxexample.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@PostMapping("/employees")
	public Mono<Employee> createEmployees(@Valid @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	@GetMapping(value = "/employees", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ResponseEntity<Employee>> getEmployees() {
		return employeeRepository.findAll().map(employees -> ResponseEntity.ok(employees))
				.defaultIfEmpty(ResponseEntity.noContent().build());
	}

	@PutMapping(value = "/employees/{id}")
	public Mono<ResponseEntity<Employee>> updateEmployees(@PathVariable("id") String id,
			@Valid @RequestBody Employee employee) {

		return employeeRepository.findById(id).flatMap(existEmployee -> {
			existEmployee.setName(employee.getName());
			existEmployee.setRole(employee.getRole());
			return employeeRepository.save(existEmployee);
		}).map(updateEmployee -> new ResponseEntity<>(updateEmployee, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping(value = "/employees/{id}")
	public Mono<ResponseEntity<Void>> deleteEmployees(@PathVariable("id") String id) {

		return employeeRepository.findById(id).flatMap(existEmployee -> {
			return employeeRepository.deleteById(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
		}).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/employees/{id}")
	public Mono<ResponseEntity<Employee>> getEmployeesById(@PathVariable("id") String id) {
		return employeeRepository.findById(id).map(emplo -> ResponseEntity.ok(emplo))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
}
