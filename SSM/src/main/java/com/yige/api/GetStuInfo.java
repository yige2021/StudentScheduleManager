package com.yige.api;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import com.yige.classes.Account;
import com.yige.classes.StuInfo;

@WebServlet(name = "GetStuInfo", value = "/api/getstuinfo")
public class GetStuInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Gson json = new Gson();
        StuInfo myInfo = new StuInfo();
        List<StuInfo> t = new ArrayList<>();

        myInfo.lstuID = Account.stuID;
        myInfo.lstuName = Account.stuName;
        myInfo.lschool = Account.school;
        myInfo.lsubject = Account.subject;
        myInfo.lclassID = Account.classID;

        t.add(myInfo);
        String s = json.toJson(t);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(s);
    }
}