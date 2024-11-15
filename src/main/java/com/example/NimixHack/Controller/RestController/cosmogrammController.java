package com.example.NimixHack.Controller.RestController;

import com.example.NimixHack.DTO.CosmogramPersonDTO;
import com.example.NimixHack.Model.Employee;
import com.example.NimixHack.Service.employeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cosmograms/api")
public class cosmogrammController {

    @Autowired
    private employeeService operationsEmployee;

    @PostMapping("/transDTO/{employeeId}")
    public CosmogramPersonDTO dto(@RequestBody CosmogramPersonDTO cosmogramPersonDTO, @PathVariable("employeeId") int employeeId) {
        Employee employee = operationsEmployee.getConcreteEmployee(employeeId);
        cosmogramPersonDTO.setName(employee.getName());
        String[] birthDateParts = employee.getBirth_date().split("-");
        int year = Integer.parseInt(birthDateParts[0]);
        int month = Integer.parseInt(birthDateParts[1]);
        int day = Integer.parseInt(birthDateParts[2]);
        cosmogramPersonDTO.setYear(year);
        cosmogramPersonDTO.setMonth(month);
        cosmogramPersonDTO.setDay(day);
        cosmogramPersonDTO.setCity(employee.getBirth_place());
        cosmogramPersonDTO.setZodiac_type("Tropic");

        return cosmogramPersonDTO;
    }
    @PostMapping("/getCosmogramm")
    public String getCosmogram(@RequestBody CosmogramPersonDTO dto) {
        // Создаем объект для отправки
        Map<String, Object> subject = new HashMap<>();
        subject.put("name", dto.getName());
        subject.put("year", dto.getYear());
        subject.put("month", dto.getMonth());
        subject.put("day", dto.getDay());
        subject.put("hour", dto.getHour());
        subject.put("minute", dto.getMinute());
        subject.put("longitude", dto.getLongitude());
        subject.put("latitude", dto.getLatitude());
        subject.put("city", dto.getCity());
        subject.put("nation", dto.getNation());
        subject.put("timezone", dto.getTimezone());
        subject.put("zodiac_type", "Tropic"); // Устанавливаем тип зодиака

        // Оборачиваем в общий объект
        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("subject", subject);

        // Логируем запрос
        try {
            System.out.println("Отправляем запрос: " + new ObjectMapper().writeValueAsString(requestBodyMap));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");

        // Создаем тело запроса
        okhttp3.RequestBody body;
        try {
            String jsonBody = new ObjectMapper().writeValueAsString(requestBodyMap);
            body =  okhttp3.RequestBody.create(mediaType, jsonBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Request request = new Request.Builder()
                .url("https://astrologer.p.rapidapi.com/api/v4/birth-chart")
                .post(body)
                .addHeader("x-rapidapi-key", "---")
                .addHeader("x-rapidapi-host", "astrologer.p.rapidapi.com")
                .addHeader("Content-Type", "application/json")
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            String responseBody = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(responseBody);
            JsonNode chartNode = jsonNode.get("chart");
            if (chartNode != null) {
                return chartNode.toString(); // Возвращаем значение по ключу "chart" как строку
            } else {
                return "Ключ 'chart' не найден в ответе.";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
