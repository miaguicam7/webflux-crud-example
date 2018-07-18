package com.example.webfluxexample.DAO;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.webfluxexample.model.Employee;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee,String>{	
}
