package com.yige.api;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.google.gson.Gson;

import com.yige.classes.Timer2;
import com.yige.method.Student;

@WebServlet(name = "GetTime", value = "/api/gettime")
public class GetTime extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Timer2 time = new Timer2();

        time.w = Student.time.week;
        time.d = Student.time.day;
        time.h = Student.time.hour;

        Gson json = new Gson();
        String s = json.toJson(time);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(s);
    }
}