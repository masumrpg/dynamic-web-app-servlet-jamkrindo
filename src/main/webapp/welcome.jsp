<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.enigma.dynamic_web_app.dao.StudentData" %>
<%@ page import="com.enigma.dynamic_web_app.model.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        <%@ include file="/styling/welcome.css"%>
    </style>
    <script>
        $(document).ready(function() {
            $(".student-id-link").click(function(e) {
                e.preventDefault();
                const studentId = $(this).text();
                showStudentPopup(studentId);
            });
        });

        function showStudentPopup(studentId) {
            const popup = $("#studentPopup");
            const popupContent = $("#studentPopupContent");
            const overlay = $(".popup-overlay");

            const studentData = <%= StudentData.getStudentsJson() %>;

            const selectedStudent = studentData.find(student => student.id === studentId);

            if (selectedStudent) {
                popupContent.html(
                    "<strong>Student ID:</strong> " + selectedStudent.id + "<br>" +
                    "<strong>Student Name:</strong> " + selectedStudent.name + "<br>" +
                    "<strong>Department:</strong> " + selectedStudent.department + "<br>" +
                    "<strong>Marks:</strong> " + selectedStudent.marks + "<br>" +
                    "<strong>Pass %:</strong> " + selectedStudent.passPercentage + "%"
                );
                overlay.show();
                popup.show();
            }
        }

        function closeStudentPopup() {
            const popup = $("#studentPopup");
            const overlay = $(".popup-overlay");

            overlay.hide();
            popup.hide();
        }
    </script>
</head>
<body>
<h1>Welcome, ${userId}!</h1>
<table>
    <thead>
    <tr>
        <th>Department</th>
        <th>Student ID</th>
        <th>Student Name</th>
        <th>Marks</th>
        <th>Pass %</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Student> students = StudentData.getStudents();
        for (Student student : students) {
            int passPercentage = student.getMarks() >= 40
                    ? (100 * StudentData.getPassCount(student.getDepartment()) / StudentData.getTotalCount(student.getDepartment()))
                    : 0;
    %>
    <tr>
        <td><%= student.getDepartment() %></td>
        <td><a href="#" class="student-id-link"><%= student.getId() %></a></td>
        <td><%= student.getName() %></td>
        <td><%= student.getMarks() %></td>
        <td><%= passPercentage %>%</td>
    </tr>
    <% } %>
    </tbody>
</table>

<div class="popup-overlay"></div>
<div id="studentPopup" class="student-popup">
    <div class="student-popup-content" id="studentPopupContent"></div>
    <button onclick="closeStudentPopup()">Close</button>
</div>
</body>
</html>
