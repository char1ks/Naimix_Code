package com.example.NimixHack.DTO;

public class CosmogramPersonDTO {
    private String name;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private double longitude;
    private double latitude;
    private String city;
    private String nation;
    private String timezone;
    private String zodiac_type;

    // Конструктор
    public CosmogramPersonDTO() {
    }

    public CosmogramPersonDTO(String name, int year, int month, int day, int hour, int minute, double longitude, double latitude, String city, String nation, String timezone, String zodiac_type) {
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.nation = nation;
        this.timezone = timezone;
        this.zodiac_type = zodiac_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getZodiac_type() {
        return zodiac_type;
    }

    public void setZodiac_type(String zodiac_type) {
        this.zodiac_type = zodiac_type;
    }
}