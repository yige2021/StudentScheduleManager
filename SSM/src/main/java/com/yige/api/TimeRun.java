package com.yige.api;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.yige.classes.Stu_CourseList;
import com.yige.classes.Task;
import com.yige.classes.TaskList;
import com.yige.method.Student;
import com.yige.system.File;

@WebServlet(name = "TimeRun", value = "/api/timerun")
public class TimeRun extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String week = request.getParameter("week");        //上课周
        String day = request.getParameter("day");        //上课日
        String hour = request.getParameter("hour");        //上课时间

        Student.time.week = Integer.parseInt(week);
        Student.time.day = Integer.parseInt(day);
        Student.time.hour = Integer.parseInt(hour);

        if (Student.time.hour == 6) {
            Student.temps.clear();
            Student.itemList.clear();

            for (int i = 0; i < 24; i++) {
                Student.itemList.add(i, "");
            }

        }

        File.saveTime();

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        String s = "";
        int t = Student.time.hour;
        String a = Student.itemList.get(t);
        String b = Student.itemList.get(t+1);
        if (a.equals(b) == false && "".equals(b) == false) {
            s = "您的日程：\"" + b + "\"即将开始！请做好准备";
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(s);
    }
}