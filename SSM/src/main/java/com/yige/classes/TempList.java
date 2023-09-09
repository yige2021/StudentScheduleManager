package com.yige.classes;

import com.yige.method.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TempList {
    public String type;
    public String location;
    public String time;

    public static List<TempList> list = new ArrayList<>();

    public static List<TempList> getTempList() {
        list.clear();
        ListIterator<Temp> iter = Student.temps.listIterator();

        while (iter.hasNext()) {
            Temp element = iter.next();
            TempList item = new TempList();

            item.type = Student.tempInfo.get(element.tempType).label;
            item.location = Account.locInfo.get(element.tempLocation).label;
            if (element.tempTime < 10) {
                item.time = "0" + element.tempTime + ":00";
            }
            else {
                item.time = element.tempTime + ":00";
            }
            list.add(item);
        }
        return list;
    }
}
