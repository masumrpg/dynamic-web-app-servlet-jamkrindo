package com.enigma.dynamic_web_app;

import com.enigma.dynamic_web_app.dao.StudentData;
import com.enigma.dynamic_web_app.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        if (userId.equals("admin") && password.equals("password")) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);

            response.sendRedirect("welcome.jsp");
        } else {
            if (isValidStudent(userId, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);

                response.sendRedirect("welcome.jsp");
            } else {
                request.setAttribute("errorMessage", "Invalid user ID or password");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }

    private boolean isValidStudent(String userId, String password) {
        List<Student> students = StudentData.getStudents();

        for (Student student : students) {
            if (student.getId().equals(userId) && "password".equals(password)) {
                return true;
            }
        }
        return false;
    }
}