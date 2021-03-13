package com.SDAprojects.ToDoLy;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class WriteAsData {

    public static void writeAsData(Tasker tasker) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File("dataFile.txt");
        String json = gson.toJson(tasker);

        try {
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(json);
            bufferedWriter.close();
        } catch (Exception e) {
        }
    }
}
