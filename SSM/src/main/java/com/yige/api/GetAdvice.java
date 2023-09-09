package com.yige.api;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.yige.method.Admin;
import com.yige.method.Student;

@WebServlet(name = "GetAdvice", value = "/api/getadvice")
public class GetAdvice extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        String s = "建议时间：";
        int num = 0;

        for(int i = 6; i <= 22; i++) {
            if ("".equals(Student.itemList.get(i)) && num < 3) {
                num++;
                s = s + i + ":00 ";
            }
        }

        if ("建议时间：".equals(s)) {
            s = "无可用时间";
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(s);
    }
}