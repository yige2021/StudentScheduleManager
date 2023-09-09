package com.yige.method;

import com.yige.algorithm.Trie;
import com.yige.classes.Course;
import com.yige.system.File;

import java.util.*;

public class Admin {
    public static LinkedList<Course> courses = new LinkedList<Course>();
    public static int latestID = 0;

    public static  Trie[] stuList = new Trie[1000];
    public static int listSize = 0;
    public static void init() {
        //反序列化
        File.adminDeseli();
        File.readLocation(true);

        if (courses.size() == 0) {
            Course headNode = new Course();
            headNode.courseID = 0;
            courses.add(headNode);
        }

        //从链表的头结点读取最新ID
        latestID = courses.getFirst().courseID;

//        for(int i = 0; i < 1000; i++) {
//            Admin.stuList[i].len = 0;
//            Admin.stuList[i].isEnd = false;
//        }

    }

    public static Course courseInfo(int id, String cs, String cle, String clo, String el, String cn, String ed, String ew, String et, String cw, String cd, String ct ) {
        Course myCourse = new Course();

        myCourse.courseID = id;
        myCourse.courseScore = Integer.parseInt(cs);
        myCourse.courseLength = Integer.parseInt(cle);
        myCourse.courseLocation = Integer.parseInt(clo);
        myCourse.examLocation = Integer.parseInt(el);
        myCourse.courseName = cn;
        myCourse.examWeek = Integer.parseInt(ew);
        myCourse.examDay = Integer.parseInt(ed);
        myCourse.examTime = Integer.parseInt(et);
        myCourse.courseWeek = Integer.parseInt(cw);
        myCourse.courseDay = Integer.parseInt(cd);
        myCourse.courseTime = Integer.parseInt(ct);

        return myCourse;
    }

    //获取指定编号课程的迭代器
    public static ListIterator<Course> getCourse(int id) {
        ListIterator<Course> iter = courses.listIterator();
        boolean isFirst = true;
        while (iter.hasNext()) {
            Course element = iter.next();
            if (id == latestID && latestID > 1 && isFirst == true) {
                isFirst = false;
                continue;
            }
            if (id == 1 && isFirst == true) {
                break;
            }
            if (element.courseID == id) {
                break;
            }
        }
        return iter;
    }

    //把给定的数据打包成一个课程对象
    public static void addCourse(String cs, String cle, String clo, String el, String cn, String ed, String ew, String et, String cw, String cd, String ct) {
        latestID++;
        courses.add(courseInfo(latestID, cs, cle, clo, el, cn, ed, ew, et, cw, cd, ct));
        System.out.println("成功增加课程！目前最新课程的编号：" + latestID);
    }

    //编辑课程信息
    public static void editCourse(String id, String cs, String cle, String clo, String el, String cn, String ed, String ew, String et, String cw, String cd, String ct) {
        int id_int = Integer.parseInt(id);
        ListIterator<Course> iter = getCourse(id_int);
        iter.set(courseInfo(Integer.parseInt(id), cs, cle, clo, el, cn, ed, ew, et, cw, cd, ct));
    }

    public static void deleteCourse(String id) {
        int id_int = Integer.parseInt(id);
        ListIterator<Course> iter = getCourse(id_int);
        iter.remove();
    }

    public static Course queryCourse(int id) {
        ListIterator<Course> iter = courses.listIterator();
        boolean isFirst = true;
        while (iter.hasNext()) {
            Course element = iter.next();
            if (isFirst == true && id == latestID && latestID > 1) {
                isFirst = false;
                continue;
            }
            if (isFirst == true && id == 1) {
                return element;
            }
            if (element.courseID == id) {
                return element;
            }
        }
        return null;
    }

    public static boolean checkCollision(int courseID, int cw, int cd, int ct) {
        ListIterator<Course> iter = courses.listIterator();
        System.out.println(courseID);
        while (iter.hasNext()) {
            Course element = iter.next();
            if (element.courseID == courseID) {
                continue;
            }
            if (element.courseDay == cd && ct >= element.courseTime && ct < element.courseTime + element.courseLength) {
                if (element.courseWeek == 0) {
                    return true;
                }
                else if(element.courseWeek != 0) {
                    if (cw == 0) {
                        return true;
                    }
                    else if (cw != 0){
                        if (cw == element.courseWeek) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}