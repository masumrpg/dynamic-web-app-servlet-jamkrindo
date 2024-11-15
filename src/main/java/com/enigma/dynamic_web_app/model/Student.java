package com.enigma.dynamic_web_app.model;

public class Student {
    private String id;
    private String name;
    private String department;
    private int marks;
    private int passPercentage;

    public Student(String id, String name, String department, int marks, int passPercentage) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.marks = marks;
        this.passPercentage = passPercentage;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getMarks() {
        return marks;
    }

    public int getPassPercentage() {
        return passPercentage;
    }
}