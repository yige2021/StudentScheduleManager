package com.yige.api;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

import com.yige.algorithm.DP_Main;
import com.yige.system.Logger;

@WebServlet(name = "Navigation", value = "/api/navigation")
public class Navigation extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建字段
        String startPoint = request.getParameter("startPoint");
        String midPoint = request.getParameter("midPoint");
        String endPoint = request.getParameter("endPoint");

        //配置跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        for(int i = 0; i < 12; i++) {
            DP_Main.p[i] = 0;
        }
        DP_Main.p[0] = Integer.parseInt(startPoint);

        int total = 0;

        if ("-1".equals(midPoint) == false) {
            String[] points = midPoint.split(",");

            for (String s_num : points) {
                total++;
                int num = Integer.parseInt(s_num);
                DP_Main.p[total] = num;
            }
        }

        DP_Main.p[++total] = Integer.parseInt(endPoint);
        DP_Main.num = ++total;

        String s = DP_Main.entrance(startPoint, endPoint);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        //创建输出流
        PrintWriter printWriter = response.getWriter();
        response.getWriter().write(s);
        Logger.writeLog("Navigation", "Start point: " + startPoint + ", Mid points: " + midPoint + ", End point: " + endPoint);
    }
}
