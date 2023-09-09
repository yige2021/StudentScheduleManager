package com.yige.classes;

import com.yige.method.Admin;
import com.yige.method.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CourseList {
    public int id;
    public int score;
    public String name;

    public static List<CourseList> list = new ArrayList<>();

    public static List<CourseList> getCourseList() {
        list.clear();
        ListIterator<Course> iter = Admin.courses.listIterator();
        boolean isFirst = true;
        while (iter.hasNext()) {
            Course element = iter.next();
            CourseList item = new CourseList();

            if (isFirst == true) {
                isFirst = false;
                item.id = 1;
            }

            else {
                item.id = element.courseID;
            }
            item.score = element.courseScore;
            item.name = element.courseName;

            if (item.name != null) {
                list.add(item);
            }
        }
        return list;
    }

}
