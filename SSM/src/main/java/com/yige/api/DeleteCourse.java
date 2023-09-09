package com.yige.api;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import com.yige.method.Admin;
import com.yige.system.File;

@WebServlet(name = "DeleteCourse", value = "/api/deletecourse")
public class DeleteCourse extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建字段
        String id = request.getParameter("courseID");        //课程编号

        //创建输出流
        PrintWriter printWriter = response.getWriter();

        //配置跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Admin.deleteCourse(id);
        File.adminSeli();
//
    }
}
