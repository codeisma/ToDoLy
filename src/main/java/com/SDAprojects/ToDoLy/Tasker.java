package com.SDAprojects.ToDoLy;

import java.util.ArrayList;

public class Tasker {
    private ArrayList<Task> listOfTasks = new ArrayList<>();


    public void createTask(Task task) {
        listOfTasks.add(task);
    }

    public void deleteTask(String title) {
        for (Task task : listOfTasks) {
            if (task.title.equalsIgnoreCase(title)) {
                listOfTasks.remove(task);
                break;
            }
        }
    }

    public void showTasks() {
        for (Task listOfTask : listOfTasks) {
            System.out.println(listOfTask.toString());
        }
    }


    // edit task method
    // sorting
    // saving to file


}
