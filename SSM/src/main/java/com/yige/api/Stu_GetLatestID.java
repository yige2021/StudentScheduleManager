package com.yige.api;

import com.google.gson.Gson;
import com.yige.classes.Stu_CourseList;
import com.yige.classes.Timer2;
import com.yige.method.Admin;
import com.yige.method.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Stu_GetLatestID", value = "/api/stu_getlatestid")
public class Stu_GetLatestID extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        response.getWriter().write(Integer.toString(Student.latestTaskID));
    }
}