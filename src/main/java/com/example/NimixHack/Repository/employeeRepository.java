package com.example.NimixHack.Repository;

import com.example.NimixHack.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface employeeRepository extends JpaRepository<Employee,Integer> {
    List<Employee> findByTeamIdIsNotNull();
}
