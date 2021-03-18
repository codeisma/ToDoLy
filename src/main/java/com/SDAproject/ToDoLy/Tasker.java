package com.SDAproject.ToDoLy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is Tasker class. A
 *
 * @author ismailcelik
 * @version 17.05.2021
 */
public class Tasker implements Serializable {

    private ArrayList<Task> listOfTasks = new ArrayList<>();

    /**
     * Creates the new task and adds to listOfTasks
     * @param task
     */
    public void createTask(Task task) {
        listOfTasks.add(task);
    }

    /**
     * Removes the task from listOfTasks.
     * @param task
     */
    public void deleteTask(Task task) {
        listOfTasks.remove(task);
    }

    /**
     * Sets the task as Done.
     * @param task The task to set as done.
     */
    public void setAsDone(Task task) {
        task.setStatus(true);
    }

    /**
     * Returns all tasks information.
     * @return The list of tasks.
     */
    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }

    /**
     * Returns the numbers of all tasks in listOfTasks.
     * @return The size of list of tasks.
     */
    public int getSize() {
        return listOfTasks.size();
    }

    /**
     * Return a boolean to identify wether or not there are tasks in the list.
     * @return The value true or false.
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * Sorts the list by due date.
     * @return The list of tasks sorted by due date.
     */
    public ArrayList<Task> SortByDate() {
        Collections.sort(listOfTasks);
        return listOfTasks;
    }

    /**
     * Sorts the list by name
     * @param project The project to filter.
     * @return New list of task filter by project.
     */
    public List<Task> SortByName(String project) {
        List<Task> tasksByFilter = listOfTasks.stream().filter(task -> task.getProject().equals(project))
                .collect(Collectors.toList());
        return tasksByFilter;
    }

    /**
     * Returns the list of projects.
     * @return The list of projects.
     */
    public ArrayList<String> getProjects() {
        ArrayList<String> projects = new ArrayList<>();
        for (Task p : listOfTasks) {
            if (!projects.contains(p.getProject())) {
                projects.add(p.getProject());
            }
        }
        return projects;
    }

    /**
     * Returns the number of tasks mark as done.
     * @return The number of tasks done.
     */
    public int getDoneTasks() {
        int numberOfTaskIsDone = 0;
        for (Task task : listOfTasks) {
            if (task.getStatus().equals(true)) {
                numberOfTaskIsDone++;
            }
        }
        return numberOfTaskIsDone;
    }
}