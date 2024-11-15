package com.example.NimixHack.Controller.RestController;

import com.example.NimixHack.Model.Employee;
import com.example.NimixHack.Model.TarotCard;
import com.example.NimixHack.Model.team;
import com.example.NimixHack.Service.LLMControl;
import com.example.NimixHack.Service.employeeService;
import com.example.NimixHack.Service.teamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/team/api")
public class teamController {

    @Autowired
    private teamService operations;

    @Autowired
    private employeeService operationsEmployee;

    @Autowired
    private LLMControl ai;

    @GetMapping("/getAll_teams")
    public List<team> getAll(){
        return operations.getAllTeams();
    }
    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody team team){
        operations.saveTeam(team);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping("/choose/{teamId}/{employeeId}")
    public ResponseEntity<String> choose(@PathVariable("teamId") int teamId,@PathVariable("employeeId") int employeeId){
        Employee employee=operationsEmployee.getConcreteEmployee(employeeId);
        team team=operations.getConcreteTeam(teamId);

        List<Employee> updatedEmployees = new ArrayList<>(team.getEmployees());

        List<TarotCard> allEmployeeCards = new ArrayList<>();

        for (Employee employeeAlso : updatedEmployees) {
            allEmployeeCards.addAll(employeeAlso.getCards());
        }

        String allEmployeeCardsString = allEmployeeCards.stream()
                .map(TarotCard::getName)
                .collect(Collectors.joining(", "));

        List<TarotCard> newEmployeeCards = employee.getCards();
        String newEmployeeCardsString = newEmployeeCards.stream()
                .map(TarotCard::getName)
                .collect(Collectors.joining(", "));

        String total = ai.generate("Привет! У меня есть уникальное и завлекающее задание: подбирать людей по сходствам карт Таро. Твоя задача: сравнить, смогут ли сработаться данные люди исходя из трех карт Таро, которые они вытащили. Вот карты Таро всех сотрудников: " + allEmployeeCardsString + ". А вот карты Таро новобранца: " + newEmployeeCardsString + ". Давай мне ответ на русском языке, а также ответ не должен содержать больше 72 слов");

        employee.setTeam(team);
        updatedEmployees.add(employee); // Добавляем нового сотрудника
        team.setEmployees(updatedEmployees); // Устанавливаем обновленный список сотрудников

        operations.updateTeam(teamId,team);
        operationsEmployee.updateEmployee(employeeId,employee);

        return ResponseEntity.ok(total);
    }
    @GetMapping("/get_perfect_teams")
    public String getPerfectTeam(){
        System.out.println(operationsEmployee.getAllEmployees());
        String total = ai.generate("Сформируй команды от 2 до 7 человек из списка сотрудников. Проанализируй их карты Таро и создай команды, которые будут хорошо сочетаться. Вот список сотрудников: " + operationsEmployee.getAllEmployees() + ". Пример ответа: Команда 'Ястреб' - крутой парень 1, ... . ТЕБЕ НЕ НУЖНО ДАВАТЬ МНЕ АКОЙ-ЛИБО КОД.ПОВТОРЯЮСЬ,ТВОЯ ЗАДАЧА-ПРОАНАЛИЗИРОВАТЬ И ДАТЬ МНЕО ТВЕТ,ИСХОДЯ ИЗ ТВОЕЙ ТОЧКИ ЗРЕНИЯ.НАПРИМЕР,У ДВУХ СОТРУДНИКОВ ЕСТЬ КАРТА МИР,ТО ЭТО ЗНАЧИТ,ЧТО ОНИ ЧЕМТО ПОХОДИ.ПО ТАКОМУ ПРИНЦИПУ И ПОДБИРАЙ КОМАНДЫ");
        return total;
    }
}
