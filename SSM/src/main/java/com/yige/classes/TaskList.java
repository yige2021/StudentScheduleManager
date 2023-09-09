package com.yige.classes;

import com.yige.method.Admin;
import com.yige.method.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TaskList {
    public String type;
    public String location;
    public String time;

    public static List<TaskList> list = new ArrayList<>();

    public static List<TaskList> getTaskList() {
        list.clear();
        ListIterator<Task> iter = Student.tasks.listIterator();

        while (iter.hasNext()) {
            Task element = iter.next();
            TaskList item = new TaskList();
            item.type = Student.taskInfo.get(element.taskType).label;

            int t = element.taskTime;
            if (t == 0) {
                continue;
            }
            if (element.taskDay != Student.time.day) {
                continue;
            }
            if (t < 10) {
                item.time = "0" + Integer.toString(t) + ":00";
            }
            else {
                item.time = Integer.toString(t) + ":00";
            }

            item.location = Account.locInfo.get(element.taskLocation).label;
            list.add(item);
            Student.itemList.set(t, item.type);
        }
        return list;
    }
}
