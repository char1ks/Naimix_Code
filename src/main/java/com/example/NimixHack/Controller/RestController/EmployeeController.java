package com.example.NimixHack.Controller.RestController;

import com.example.NimixHack.Model.Employee;
import com.example.NimixHack.Model.TarotCard;
import com.example.NimixHack.Service.TarotCardService;
import com.example.NimixHack.Service.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/api")
public class EmployeeController {

    @Autowired
    private employeeService operations;

    @Autowired
    private TarotCardService operationsTarot;

    @GetMapping("/getAllWithTeamId")
    public List<Employee> getAllWithTeam(){
        return operations.getAllWhereTeamIdIsNotNull();
    }
    @PostMapping("/save")
    public int saveEmployee(@RequestBody Employee employee){
        operations.saveEmployee(employee);
        return employee.getId();
    }

    @PostMapping("/setTarot/{employeeId}")
    public ResponseEntity<HttpStatus> setTarot(@PathVariable("employeeId") int employeeId, @RequestBody List<TarotCard> tarotToSet) {
        System.out.println(employeeId);
        Employee employee = operations.getConcreteEmployee(employeeId);
        employee.setCards(tarotToSet);
        operations.saveEmployee(employee);
        for (TarotCard tarotCard : tarotToSet) {
            List<Employee> currentEmployees = tarotCard.getEmployees();
            if(currentEmployees!=null)
                if (!currentEmployees.contains(employee)) {
                    currentEmployees.add(employee);
                    tarotCard.setEmployees(currentEmployees);
                }
            operationsTarot.saveCard(tarotCard);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
