package com.enigma.dynamic_web_app.dao;

import com.enigma.dynamic_web_app.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentData {

    public static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("S1", "Student 1", "Dep 1", 35, 75));
        students.add(new Student("S2", "Student 2", "Dep 1", 70, 70));
        students.add(new Student("S3", "Student 3", "Dep 1", 60, 60));
        students.add(new Student("S4", "Student 4", "Dep 1", 90, 90));
        students.add(new Student("S5", "Student 5", "Dep 2", 30, 0));
        students.add(new Student("S6", "Student 6", "Dep 2", 32, 0));
        students.add(new Student("S7", "Student 7", "Dep 3", 70, 33));
        students.add(new Student("S8", "Student 8", "Dep 3", 20, 0));
        return students;
    }

    public static String getStudentsJson() {
        List<Student> students = getStudents();
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            int passPercentage = student.getMarks() >= 40
                    ? (100 * getPassCount(student.getDepartment()) / getTotalCount(student.getDepartment()))
                    : 0;

            jsonBuilder.append("{")
                    .append("\"id\":\"").append(student.getId()).append("\",")
                    .append("\"name\":\"").append(student.getName()).append("\",")
                    .append("\"department\":\"").append(student.getDepartment()).append("\",")
                    .append("\"marks\":").append(student.getMarks()).append(",")
                    .append("\"passPercentage\":").append(passPercentage)
                    .append("}");

            if (i < students.size() - 1) {
                jsonBuilder.append(",");
            }
        }

        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }

    public static int getPassCount(String department) {
        int passCount = 0;
        for (Student student : getStudents()) {
            if (student.getDepartment().equals(department) && student.getMarks() >= 40) {
                passCount++;
            }
        }
        return passCount;
    }

    public static int getTotalCount(String department) {
        int totalCount = 0;
        for (Student student : getStudents()) {
            if (student.getDepartment().equals(department)) {
                totalCount++;
            }
        }
        return totalCount;
    }
}
