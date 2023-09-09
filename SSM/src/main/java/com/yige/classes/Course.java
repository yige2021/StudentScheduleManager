package com.yige.classes;

import java.io.Serializable;

public class Course implements Serializable{
    public boolean isSingle;
    public int courseID;           //课程编号
    public int courseScore;        //课程学分
    public int courseLength;       //课程长度
    public int courseLocation;     //上课地点
    public int examLocation;       //考试地点
    public int courseWeek;       //课程周次
    public int courseDay;       //课程日次
    public int courseTime;      //课程时间
    public int examWeek;       //考试周次
    public int examDay;       //考试日次
    public int examTime;      //考试时间
    public String courseName;      //课程名称
}
