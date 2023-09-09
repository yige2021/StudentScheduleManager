package com.yige.system;

import com.yige.classes.Account;
import com.yige.classes.Course;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import com.yige.algorithm.Floyd;
import com.yige.method.Admin;
import com.yige.classes.*;
import com.yige.method.Student;

public class File {

    public static void adminDeseli() {
        String path = "C:\\Users\\yige_\\Documents\\SSM Files\\All Users\\courses.ser";
        java.io.File courseFile = new java.io.File(path);

        //若课程链表对象存在，则反序列化对象到courses
        if (courseFile.exists()) {
            try (FileInputStream fileIn = new FileInputStream(path);
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {
                Admin.courses = (LinkedList<Course>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void adminSeli() {
        String path = "C:\\Users\\yige_\\Documents\\SSM Files\\All Users\\courses.ser";

        ListIterator<Course> iter = Admin.courses.listIterator();
        Course headNode = iter.next();
        headNode.courseID = Admin.latestID;
        iter.set(headNode);

        try
        {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(Admin.courses);
            out.close();
            fileOut.close();
            System.out.printf("已保存到" + path + '\n');
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }

    public static List<LocItem> readLocation(boolean isFull) {
        String path1 = "C:/Users/yige_/Documents/SSM Files/All Users/course_location.txt";
        String path2 = "C:/Users/yige_/Documents/SSM Files/All Users/location.txt";
        List<LocItem> locInfo = new ArrayList<>();
        BufferedReader reader;
        try {
            if (isFull == false) {
                reader = new BufferedReader(new FileReader(path1));
            }
            else {
                reader = new BufferedReader(new FileReader(path2));
            }
            String line;
            int id = 0;

            while ((line = reader.readLine()) != null) {
                LocItem item = new LocItem();
                item.value = id++;
                item.label = line;
                locInfo.add(item);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isFull == true) {
            Account.locInfo = locInfo;
        }
        return locInfo;
    }

    public static List<TaskItem> readTask() {
        List<TaskItem> taskInfo = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "C:/Users/yige_/Documents/SSM Files/All Users/task_info.txt"));
            String line;
            int id = 0;
            while ((line = reader.readLine()) != null) {
                TaskItem item = new TaskItem();
                item.value = id++;
                item.label = line;
                taskInfo.add(item);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Student.taskInfo = taskInfo;
        return taskInfo;
    }

    public static List<TaskItem> readTemp() {
        List<TaskItem> tempInfo = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "C:/Users/yige_/Documents/SSM Files/All Users/temp_info.txt"));
            String line;
            int id = 0;
            while ((line = reader.readLine()) != null) {
                TaskItem item = new TaskItem();
                item.value = id++;
                item.label = line;
                tempInfo.add(item);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Student.tempInfo = tempInfo;
        return tempInfo;
    }

    public static void readStuInfo() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "C:/Users/yige_/Documents/SSM Files/" +
                            Account.stuID + "/user_info.txt"));
            String line = reader.readLine();
            int num = 0;
            while (line != null) {
                if (num == 1) {
                    Account.stuID = line;
                }
                if (num == 2) {
                    Account.stuName = line;
                }
                if (num == 3) {
                    Account.school = line;
                }
                if (num == 4) {
                    Account.subject = line;
                }
                if (num == 5) {
                    Account.classID = line;
                }
                num++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readTime() {
        BufferedReader reader;
        try {

            reader = new BufferedReader(new FileReader(
                    "C:/Users/yige_/Documents/SSM Files/" + Account.stuID + "/time.txt"));

            String line;
            int id = 0;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (id == 0) {
                    Student.time.week = Integer.parseInt(line);
                }
                else if (id == 1) {
                    Student.time.day = Integer.parseInt(line);
                }
                else if (id == 2) {
                    Student.time.hour = Integer.parseInt(line);
                }
                id++;
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTime() {
        try {
            String path = "C:/Users/yige_/Documents/SSM Files/" + Account.stuID + "/time.txt";
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(Files.newOutputStream(new java.io.File(path).toPath()), "UTF-8"));
            bw.write(Integer.toString(Student.time.week));
            bw.newLine();
            bw.write(Integer.toString(Student.time.day));
            bw.newLine();
            bw.write(Integer.toString(Student.time.hour));
            bw.newLine();
            bw.close();
        }
        catch (Exception e) {
            System.err.println("write errors :" + e);
        }
    }

    public static void stuDeseli() {
        String path = "C:\\Users\\yige_\\Documents\\SSM Files\\" + Account.stuID + "\\tasks.ser";
        java.io.File taskFile = new java.io.File(path);

        //若课程链表对象存在，则反序列化对象到courses
        if (taskFile.exists()) {
            try (FileInputStream fileIn = new FileInputStream(path);
                 ObjectInputStream in = new ObjectInputStream(fileIn)) {
                Student.tasks = (LinkedList<Task>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void stuSeli() {
        String path = "C:\\Users\\yige_\\Documents\\SSM Files\\" + Account.stuID + "\\tasks.ser";

        ListIterator<Task> iter = Student.tasks.listIterator();
        Task headNode = iter.next();
        headNode.taskID = Student.latestTaskID;
        iter.set(headNode);

        try
        {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(Student.tasks);
            out.close();
            fileOut.close();
            System.out.printf("已保存到" + path + '\n');
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }

    public static void readEdges() {
        try {
            // 创建文件输入流
            FileInputStream inputStream = new FileInputStream("C:/Users/yige_/Documents/SSM Files/All Users/edges.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] numbers = line.split("\\s+");

                Floyd.mp[Integer.parseInt(numbers[0])][Integer.parseInt(numbers[1])] = Integer.parseInt(numbers[2]);
                Floyd.mp[Integer.parseInt(numbers[1])][Integer.parseInt(numbers[0])] = Integer.parseInt(numbers[2]);
            }
            reader.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
