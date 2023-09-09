package com.yige.api;

import com.google.gson.Gson;
import com.yige.method.Admin;
import com.yige.method.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CheckCollision2", value = "/api/checkcollision2")
public class CheckCollision2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tw = request.getParameter("taskWeek");
        String td = request.getParameter("taskDay");
        String tt = request.getParameter("taskTime");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        int taskWeek = Integer.parseInt(tw);
        int taskDay = Integer.parseInt(td);
        int taskTime = Integer.parseInt(tt);

        int val = Student.checkCollision(taskWeek, taskDay, taskTime);

        if (val == 1) {
            response.getWriter().write("1");
        }
        else if (val == 2) {
            response.getWriter().write("2");
        }
        else {
            response.getWriter().write("0");
        }

    }
}