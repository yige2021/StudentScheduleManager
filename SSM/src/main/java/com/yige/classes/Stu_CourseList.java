package com.yige.classes;

import com.yige.method.Admin;
import com.yige.method.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Stu_CourseList {
    public int id;
    public String time;
    public String name;

    public static List<Stu_CourseList> list = new ArrayList<>();

    public static List<Stu_CourseList> getCourseList() {
        list.clear();
        ListIterator<Course> iter = Admin.courses.listIterator();
        boolean isFirst = true;
        while (iter.hasNext()) {
            Course element = iter.next();
            Stu_CourseList item = new Stu_CourseList();
            if (isFirst == true) {
                isFirst = false;
                item.id = 1;
            }
            else {
                item.id = element.courseID;
            }

            if (Student.time.day != element.courseDay) {
                continue;
            }
            String time0 = Integer.toString(element.courseTime + 7) + ":00 ~ " + Integer.toString(element.courseTime + element.courseLength + 7) + ":00";
            if (element.courseTime < 3) {
                item.time = "0" + time0;
            }
            else {
                item.time = time0;
            }
            item.name = element.courseName;

            if (item.name != null) {
                list.add(item);
                for (int i = element.courseTime + 7; i < element.courseTime + 7 + element.courseLength; i++) {
                    Student.itemList.set(i, element.courseName);
                }
            }
        }
        return list;
    }

}
