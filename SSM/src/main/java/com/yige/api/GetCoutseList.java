package com.yige.api;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import com.yige.classes.CourseList;

@WebServlet(name = "GetCourseList", value = "/api/getcourselist")
public class GetCoutseList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Gson json = new Gson();
        List<CourseList> t = CourseList.getCourseList();
        String s = json.toJson(t);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(s);
    }
}