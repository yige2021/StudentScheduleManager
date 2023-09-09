package com.yige.api;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import com.yige.method.Admin;
import com.yige.system.File;

@WebServlet(name = "SetCourse", value = "/api/setcourse")
public class SetCourse extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建字段
        String id = request.getParameter("courseID");        //课程编号
        String cs = request.getParameter("courseScore");        //课程学分
        String cle = request.getParameter("courseLength");       //课程长度
        String clo = request.getParameter("courseLocation");     //上课地点
        String el = request.getParameter("examLocation");       //考试地点
        String cn = request.getParameter("courseName");     //课程名称

        String cw = request.getParameter("courseWeek");        //上课周
        String cd = request.getParameter("courseDay");        //上课日
        String ct = request.getParameter("courseTime");        //上课时间

        String ew = request.getParameter("examWeek");        //考试周
        String ed = request.getParameter("examDay");        //考试日
        String et = request.getParameter("examTime");        //考试时间

        //创建输出流
        PrintWriter printWriter = response.getWriter();

        //配置跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        System.out.println(clo);

        if ("-1".equals(id)) {
            System.out.println("添加课程");
            Admin.addCourse(cs, cle, clo, el, cn, ed, ew, et, cw, cd, ct);
        }
        else {
            System.out.println("编辑课程");
            Admin.editCourse(id, cs, cle, clo, el, cn, ed, ew, et, cw, cd, ct);
        }

        File.adminSeli();
    }
}
