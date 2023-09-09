package com.yige.classes;
import java.io.*;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

public class Account {
    public static String stuID;
    public static String stuName;
    public static String school;
    public static String subject;
    public static String classID;

    public static List<LocItem> locInfo;
    public static int login(String userID, String password) {
        if(userID.equals("") || password.equals("")){
            return 1;   //用户名或密码为空
        }

        if (checkUser(userID) == 0) {
            return 2;   //用户名不存在
        }

        if (checkPassword(userID, password) == 0) {
            return 3;   //密码错误
        }

        return 0;   //登录成功

        //initialize.init(userID);
        //course.addCourse(userID);
    }
    public static int checkUser(String userID) {
        File folder = new File("C:/Users/yige_/Documents/SSM Files/" + userID);
        if (!folder.exists() && !folder.isDirectory()) {
            return 0;
        } else {
            return 1;
        }
    }
    public static int checkPassword(String  userID, String password) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "C:/Users/yige_/Documents/SSM Files/" + userID + "/user_info.txt"));
            String correctPwd = reader.readLine();
            reader.close();
            password = DigestUtils.sha1Hex(password);
            if (password.equals(correctPwd)) {
                return 1;
            }
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}