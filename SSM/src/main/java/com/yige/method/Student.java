package com.yige.method;

import com.yige.classes.*;
import com.yige.system.File;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static com.yige.method.Admin.courses;

public class Student {
    public static int latestTaskID = 0;
    public static Timer time;
    public static List<TaskItem> taskInfo;
    public static List<TaskItem> tempInfo;
    public static LinkedList<Task> tasks = new LinkedList<Task>();
    public static List<Temp> temps = new ArrayList<>();
    public static ArrayList<String> itemList = new ArrayList<>(24);
    public static void init() {
        File.readStuInfo();
        File.stuDeseli();

        if (tasks.size() == 0) {
            Task headNode = new Task();
            headNode.taskID = 0;
            tasks.add(headNode);
        }

        //从链表的头结点读取最新ID
        latestTaskID = tasks.getFirst().taskID;

        File.readLocation(true);
        File.readTime();

        for (int i = 0; i < 24; i++) {
            Student.itemList.add(i, "");
        }
    }

    public static void addTask(String id, String tp, String tl, String tw, String td, String tt) {
        Student.latestTaskID++;
        int id_int = Integer.parseInt(id);
        Task myTask = new Task();
        myTask.taskID = id_int;
        myTask.taskWeek = Integer.parseInt(tw);
        myTask.taskDay = Integer.parseInt(td);
        myTask.taskTime = Integer.parseInt(tt);
        myTask.taskLocation = Integer.parseInt(tl);
        myTask.taskType = Integer.parseInt(tp);

        tasks.add(myTask);
    }

    public static void deleteTask(String td, String tt) {
        ListIterator<Task> iter = tasks.listIterator();
        while (iter.hasNext()) {
            Task element = iter.next();
            if (element.taskDay == Integer.parseInt(td) && element.taskTime == Integer.parseInt(tt)) {
                iter.remove();
            }
        }
    }

    public static void addTemp(String tp, String tl, String tt) {
        Temp myTemp = new Temp();
        myTemp.tempTime = Integer.parseInt(tt);
        myTemp.tempLocation = Integer.parseInt(tl);
        myTemp.tempType = Integer.parseInt(tp);
        temps.add(myTemp);
    }

    public static int checkCollision(int tw, int td, int tt) {
        ListIterator<Course> iter = courses.listIterator();
        while (iter.hasNext()) {
            Course element = iter.next();
            if (element.courseDay == td && tt >= element.courseTime + 7 && tt < element.courseTime + element.courseLength + 7) {
                if (element.courseWeek == 0) {
                    return 1;
                }
                else if(element.courseWeek != 0) {
                    if (tw == 0) {
                        return 1;
                    }
                    else if (tw != 0){
                        if (tw == element.courseWeek) {
                            return 1;
                        }
                    }
                }
            }
        }

        ListIterator<Task> iter2 = tasks.listIterator();
        while (iter2.hasNext()) {
            Task element = iter2.next();
            if (element.taskDay == td && tt == element.taskTime) {
                if (element.taskWeek == 0) {
                    return 2;
                }
                else if(element.taskWeek != 0) {
                    if (tw == 0) {
                        return 2;
                    }
                    else if (tw != 0){
                        if (tw == element.taskWeek) {
                            return 2;
                        }
                    }
                }
            }
        }

        return 0;
    }

}