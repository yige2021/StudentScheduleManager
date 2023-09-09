package com.yige.api;

import com.google.gson.Gson;
import com.yige.classes.*;
import com.yige.method.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GetLocation", value = "/api/getcourse")
public class GetCourse extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("courseID");        //课程编号

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Course course = Admin.queryCourse(Integer.parseInt(id));
        Course2 myCourse = new Course2();

        myCourse.cl = course.courseLength;

        myCourse.cn = course.courseName;
        myCourse.cs = course.courseScore;
        myCourse.ew = course.examWeek;
        myCourse.ed = course.examDay;
        myCourse.et = course.examTime;
        myCourse.cw = course.courseWeek;
        myCourse.cd = course.courseDay;
        myCourse.ct = course.courseTime;
        myCourse.stuid = course.courseID;

        LocItem tmp = Account.locInfo.get(course.courseLocation);
        myCourse.clo = tmp.label;
        tmp = Account.locInfo.get(course.examLocation);
        myCourse.el = tmp.label;

        Gson json = new Gson();
        String s = json.toJson(myCourse);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(s);
    }
}