package com.yige.api;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import com.yige.classes.Account;
import com.yige.method.Admin;
import com.yige.method.Student;

@WebServlet(name = "loginServlet", value = "/api/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建字段
        String userID = request.getParameter("userid");
        String password = request.getParameter("password");

        //创建输出流
        PrintWriter printWriter = response.getWriter();

        //配置跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        //执行登录函数，并且获取返回值
        int loginVal = Account.login(userID, password);

        //打印登录状态码
        printWriter.println(loginVal);

        //如果登录成功，则初始化
        if (loginVal == 0) {
            if ("admin".equals(userID)) {
                Admin.init();
            }
            else {
                Account.stuID = userID;
                Student.init();
            }
        }
    }
}
