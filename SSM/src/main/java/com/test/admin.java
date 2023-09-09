package com.test;

import com.yige.method.Admin;

public class admin {
    public static void main(String[] args) {
        doit();
    }

    public static void doit() {
        for (int i = 0; i < Admin.courses.size(); i++) {
            System.out.println(Admin.courses.get(i).courseID);
        }
    }
}
