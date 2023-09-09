package com.yige.api;

import com.yige.method.Student;
import com.yige.system.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteTask", value = "/api/deletetask")
public class DeleteTask extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建字段
        String td = request.getParameter("taskDay");
        String tt = request.getParameter("taskTime");

        //创建输出流
        PrintWriter printWriter = response.getWriter();

        //配置跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Student.deleteTask(td, tt);
        File.stuSeli();
    }
}
