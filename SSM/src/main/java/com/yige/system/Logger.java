package com.yige.system;

import com.yige.algorithm.Floyd;
import com.yige.classes.Account;
import com.yige.method.Student;

import java.io.*;

public class Logger {
    public static void writeLog(String opName, String opInfo) {
        String s = "Week: " + Student.time.week + ", Day: " + Student.time.day + ", Hour: " + Student.time.hour;
        s = s + ", Operation Name: " + opName + ", Operation Information: " + opInfo;
        try {
            FileWriter fw = new FileWriter("C:\\Users\\yige_\\Documents\\SSM Files\\" + Account.stuID + "\\log.txt", true); //设置为追加模式
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(s);
            bw.newLine();  //添加换行符
            bw.flush();
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static String showLog() {
        try {
            // 创建文件输入流
            FileInputStream inputStream = new FileInputStream("C:\\Users\\yige_\\Documents\\SSM Files\\" + Account.stuID + "\\log.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String s = "";
            String line;
            while ((line = reader.readLine()) != null) {
                s = s + line + "\n";
            }
            reader.close();
            inputStream.close();
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}