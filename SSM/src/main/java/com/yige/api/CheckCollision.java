package com.yige.api;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.yige.method.Admin;

@WebServlet(name = "CheckCollision", value = "/api/checkcollision")
public class CheckCollision extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("courseID");        //上课周
        String cw = request.getParameter("courseWeek");        //上课周
        String cd = request.getParameter("courseDay");        //上课日
        String ct = request.getParameter("courseTime");        //上课时间

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        int courseID = Integer.parseInt(id);
        int courseWeek = Integer.parseInt(cw);
        int courseDay = Integer.parseInt(cd);
        int courseTime = Integer.parseInt(ct);

        if (Admin.checkCollision(courseID, courseWeek, courseDay, courseTime) == true) {
            response.getWriter().write("1");
        }

        else {
            response.getWriter().write("0");
        }

    }
}