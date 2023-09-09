package com.yige.api;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.yige.algorithm.Trie;
import com.yige.method.Admin;

@WebServlet(name = "GetStuList", value = "/api/getstulist")
public class AddStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("stuID");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        Admin.listSize = Trie.addNode(Admin.stuList, id, Admin.listSize);

    }
}