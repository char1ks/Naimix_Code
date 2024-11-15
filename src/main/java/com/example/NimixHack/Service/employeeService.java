package com.example.NimixHack.Service;

import com.example.NimixHack.Model.Employee;
import com.example.NimixHack.Repository.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class employeeService {

    @Autowired
    private employeeRepository operations;

    public List<Employee> getAllEmployees(){
        return operations.findAll();
    }
    public List<Employee> getAllWhereTeamIdIsNotNull(){
        return operations.findByTeamIdIsNotNull();
    }
    public Employee getConcreteEmployee(int id){
        return operations.findById(id).orElse(null);
    }
    public void saveEmployee(Employee employee){
        operations.save(employee);
    }
    public void updateEmployee(int id,Employee employee){
        employee.setId(id);
        operations.save(employee);
    }
    public void deleteEmployee(int id){
        operations.deleteById(id);
    }
}
