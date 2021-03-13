package com.SDAprojects.ToDoLy;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadAsData {
    public static Tasker readAsData() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("dataFile.txt");
        Tasker tasker;

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            JsonReader jsonReader = new JsonReader(inputStreamReader);

            tasker = gson.fromJson(jsonReader, Tasker.class);
        } catch (Exception e) {
            tasker = new Tasker();
        }

        return tasker;
    }






//    public static Tasker readAsData() {
////        Gson gson = new GsonBuilder().setPrettyPrinting().create();
////        File file = new File("dataFile.txt");
//        Tasker tasker;
//
//        try {
//            FileInputStream fileInputStream = new FileInputStream("dataFile.txt");
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//        } catch (Exception e) {
//            System.out.println("sdf");
//        }
//
//        return tasker;
//    }
}
