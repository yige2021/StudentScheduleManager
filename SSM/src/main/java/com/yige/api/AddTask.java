package com.yige.api;

import com.google.gson.Gson;
import com.yige.classes.Account;
import com.yige.classes.Task2;
import com.yige.method.Student;
import com.yige.system.File;
import com.yige.system.Logger;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddTask", value = "/api/addtask")
public class AddTask extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建字段
        String id = request.getParameter("taskID");        //课外活动编号
        String tp = request.getParameter("taskType");      //类型
        String tl = request.getParameter("taskLocation"); //地点

        String tw = request.getParameter("taskWeek");        //上课周
        String td = request.getParameter("taskDay");        //上课日
        String tt = request.getParameter("taskTime");        //上课时间

        //配置跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Student.addTask(id, tp, tl, tw, td, tt);
        File.stuSeli();

        Gson json = new Gson();
        Task2 myTask = new Task2();

        myTask.location = Account.locInfo.get(Integer.parseInt(tl)).label;
        myTask.type = Student.taskInfo.get(Integer.parseInt(tp)).label;

        int t = Integer.parseInt(tt);
        if (t < 10) {
            myTask.time = "0" + tt + ":00";
        }
        else {
            myTask.time = tt + ":00";
        }

        String s = json.toJson(myTask);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        response.getWriter().write(s);
        Logger.writeLog("Add task", s);
    }
}
