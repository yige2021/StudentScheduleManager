package com.yige.api;

import com.google.gson.Gson;
import com.yige.classes.Account;
import com.yige.classes.Task2;
import com.yige.method.Student;
import com.yige.system.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddTemp", value = "/api/addtemp")
public class AddTemp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建字段
        String tp = request.getParameter("tempType");      //类型
        String tl = request.getParameter("tempLocation"); //地点
        String tt = request.getParameter("tempTime");        //时间

        //配置跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Student.addTemp(tp, tl, tt);

        Gson json = new Gson();
        Task2 myTemp = new Task2();

        myTemp.location = Account.locInfo.get(Integer.parseInt(tl)).label;
        myTemp.type = Student.tempInfo.get(Integer.parseInt(tp)).label;

        int t = Integer.parseInt(tt);
        if (t < 10) {
            myTemp.time = "0" + tt + ":00";
        }
        else {
            myTemp.time = tt + ":00";
        }

        Student.itemList.set(t, myTemp.type);
        for(int i = 6; i < 23; i++){
            System.out.println(Student.itemList.get(i));
        }

        String s = json.toJson(myTemp);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        response.getWriter().write(s);
        Logger.writeLog("Add temporary task", s);
    }
}
