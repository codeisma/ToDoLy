import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.displayUserMenu();
    }

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
