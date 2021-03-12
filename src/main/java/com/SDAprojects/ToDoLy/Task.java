package com.SDAprojects.ToDoLy;

public class Task {
    String title;
    String date;
    boolean isDone = false;


    public Task(String title, String date) {
        this.title = title;
        this.date = date;

    }

    @Override
    public String toString() {
        return "Task{" + "title='" + title + '\'' + ", date='" + date + '\'' + ", status=" + isDone + '}';
    }

}
