package com.example.NimixHack.Controller.RestController;

import com.example.NimixHack.DTO.Rate_Employee_DTO;
import com.example.NimixHack.Model.Employee;
import com.example.NimixHack.Model.Rate;
import com.example.NimixHack.Service.employeeService;
import com.example.NimixHack.Service.rateService;

import org.knowm.xchart.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rate/api")
public class RateController {

    @Autowired
    private rateService operations;

    @Autowired
    private employeeService operationsEmployee;
    @PostMapping("/addRate")
    public ResponseEntity<HttpStatus> addRate(@RequestBody Rate_Employee_DTO rateEmployeeDto){
        if(operations.getByEmployeeId(rateEmployeeDto.getEmployeeId())==null) {
            Rate rate = new Rate();
            rate.setRate(rateEmployeeDto.getRate());
            rate.setEmployee(operationsEmployee.getConcreteEmployee(rateEmployeeDto.getEmployeeId()));
            operations.saveRate(rate);
        }
        else
        {
            Rate rate=operations.getByEmployeeId(rateEmployeeDto.getEmployeeId());
            rate.setRate(rateEmployeeDto.getRate());
            operations.saveRate(rate);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @GetMapping("/download-chart/category")
    public ResponseEntity<byte[]> downloadChart() {
        int countOfLikes = 0;
        int countOfDislikes = 0;
        List<Rate> rates = operations.getAllRater();
        for (Rate rate : rates) {
            if (rate.getRate().equals("like")) {
                countOfLikes++;
            } else {
                countOfDislikes++;
            }
        }

        List<String> categories = new ArrayList<>();
        categories.add("Удачные");   // Первое значение
        categories.add("Неудачные"); // Второе значение

        // Создание графика
        CategoryChart chart = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title("Пример столбчатого графика")
                .xAxisTitle("Категории")
                .yAxisTitle("Значения")
                .build();

        // Добавление данных в график
        // Для каждой категории создаем отдельный список значений
        chart.addSeries("Удачные", Collections.singletonList(categories.get(0)), Collections.singletonList(countOfLikes));
        chart.addSeries("Неудачные", Collections.singletonList(categories.get(1)), Collections.singletonList(countOfDislikes));

        // Установка цветов для столбцов
        chart.getStyler().setSeriesColors(new Color[]{Color.GREEN, Color.RED}); // Синий для Удачных, Красный для Не удачных

        // Сохранение графика в поток
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BitmapEncoder.saveBitmap(chart, baos, BitmapEncoder.BitmapFormat.PNG);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        // Подготовка ответа с графиком
        byte[] imageBytes = baos.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        try {
            String fileName = URLEncoder.encode("Статистика.png", "UTF-8");
            headers.add("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
    @GetMapping("/download-chart/pie")
    public ResponseEntity<byte[]> downloadPieChart() {
        int countOfLikes = 0;
        int countOfDislikes = 0;
        List<Rate> rates = operations.getAllRater();
        for (Rate rate : rates) {
            if (rate.getRate().equals("like")) {
                countOfLikes++;
            } else {
                countOfDislikes++;
            }
        }

        // Создание круговой диаграммы
        PieChart pieChart = new PieChartBuilder()
                .width(800)
                .height(600)
                .title("Пример круговой диаграммы")
                .build();

        // Добавление данных в круговую диаграмму
        pieChart.addSeries("Удачные", countOfLikes);
        pieChart.addSeries("Неудачные", countOfDislikes);

        // Установка цветов для секторов
        pieChart.getStyler().setSeriesColors(new Color[]{Color.GREEN, Color.RED}); // Синий для Удачных, Красный для Не удачных

        // Сохранение диаграммы в поток
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BitmapEncoder.saveBitmap(pieChart, baos, BitmapEncoder.BitmapFormat.PNG);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        // Подготовка ответа с диаграммой
        byte[] imageBytes = baos.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        try {
            String fileName = URLEncoder.encode("Статистика.png", "UTF-8");
            headers.add("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
