package com.SDAproject.ToDoLy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Menu class takes scanner input.
 *
 *    @author ismailcelik
 *    @version 17.05.2021
 */
public class Menu {

    private Scanner scanner ;
    private FileHandler fileHandler;
    private Tasker tasker;


    /**
     * Creates a file and a scanner.
     */
    public Menu()
    {
        scanner = new Scanner(System.in);
        fileHandler = new FileHandler();
        tasker = fileHandler.readAsData();
        this.mainMenu();
    }

    /**
     * Screens the main menu.
     */
    private void mainMenu() {
        int option = 0;

        while (option != 4) {
            int totalTasks = tasker.getSize();
            int DoneTasks = tasker.getDoneTasks();
            System.out.println();
            System.out.println("Welcome to ToDoLy!");
            System.out.println("You have total " + totalTasks + " tasks and "+ DoneTasks + " of them are done!");
            System.out.println("");
            System.out.println("Pick an option->");
            System.out.println("(1) Show Task List (By date or project)");
            System.out.println("(2) Add New Task");
            System.out.println("(3) Edit Task (update, mark as done, remove)");
            System.out.println("(4) Save and Exit");
            System.out.println("->");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
            }

            switch (option) {
                case 1:
                    if (tasker.isEmpty()) {
                        System.err.println("YOU DO NOT HAVE ANY TASK TO SHOW!");
                        break;
                    }
                    this.showAllToDoList();
                    break;
                case 2:
                    this.newTask();
                    break;
                case 3:
                    if (tasker.isEmpty()) {
                        System.out.println();
                        System.out.println("No tasks to edit");
                        break;
                    }
                    this.chooseTaskToUpdate();
                    break;
                case 4:
                    this.saveAndExit();
                    break;
                default:
                    System.out.println();
                    System.err.println("INVALID INPUT");
                    break;
            }

        }
    }

    /**
     * Filters all tasks by date or by project name
     */
    private void showAllToDoList() {
        int option = 0;

        while (option != 3) {
            System.out.println();
            System.out.println(">> Pick an option->");
            System.out.println(">> (1) Sort by date");
            System.out.println(">> (2) Sort by project name");
            System.out.print(">> ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
            }

            switch (option) {
                case 1:
                    sortTasksByDate();
                    mainMenu();
                    break;
                case 2:
                    chooseByProjectName();
                    mainMenu();
                    break;
                default:
                    System.err.println("INVALID INPUT");
                    break;
            }
        }
    }

    /**
     * Show task list sorted by due date.
     */
    private void sortTasksByDate() {
        System.out.println("---------------------");
        System.out.println("Task List By due date");
        System.out.println("---------------------");
        ArrayList<Task> taskListSortedByDueDate = tasker.SortByDate();
        for (Task t : taskListSortedByDueDate) {
            System.out.println(t.getDueDate()+" "+t.getTitle()+" "+t.toString()+" "+t.getProject());
        }
    }

    /**
     * Screens the list by project name and details of chosen task.
     */
    private void chooseByProjectName() {
        ArrayList<String> projects = tasker.getProjects();
        String selectedProject;
        int option = 0;

        while (true) {
            System.out.println("->Pick project->");

            for (int i = 0; i < projects.size(); i++) {
                System.out.println(">> (" + (i + 1) + ") " + projects.get(i));
            }
            System.out.print("-> ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
            }

            if (option > 0 && option <= projects.size()) {
                System.out.println("--------------------");
                System.out.println("Task List By project");
                System.out.println("--------------------");
                selectedProject = projects.get(option - 1);
                List<Task> chosenoption = tasker.SortByName(selectedProject);
                for (Task t : chosenoption) {
                    System.out.println(t.getProject()+" "+t.getDueDate()+" "+ t.toString()+" "+t.getTitle());
                }
                break;
            } else {
                System.err.println("INVALID INPUT");
            }
        }
    }

    /**
     * Add a new task to the list.
     */
    private void newTask() {

        boolean invalidTitle = true;
        String title = null;

        while (invalidTitle) {
            System.out.print("Enter a new Task title-> ");
            title = scanner.nextLine();

            if (!title.isEmpty()) {
                invalidTitle = false;
            } else {
                System.err.println("INVALID INPUT");
            }
        }

        boolean invalidDate = true;
        LocalDate localDate = null;

        while (invalidDate) {
            System.out.print("Enter a new Task's Due Date with [yyyy-mm-dd] format-> ");
            String dueDateStr = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                localDate = LocalDate.parse(dueDateStr, formatter);
                invalidDate = false;
            } catch (Exception e) {
                System.err.println("INVALID INPUT");
            }
        }

        Boolean status = false;

        boolean invalidProject = true;
        String project = null;

        while (invalidProject) {
            System.out.print("Enter a project-> ");
            project = scanner.nextLine();

            if (!project.isEmpty()) {
                invalidProject = false;
            } else {
                System.err.println("INVALID INPUT");
            }
        }
        Task newTask = new Task(title, localDate, status, project);
        tasker.createTask(newTask);
    }

    /**
     * Screens all tasks with numbers.
     */
    private void chooseTaskToUpdate() {
        ArrayList<Task> tasks = tasker.getListOfTasks();
        int option = 0;

        while (true) {
            System.out.println("Pick task to Edit->");

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println(">> (" + (i + 1) + ") " + task.getDueDate() +"  "+ task.getTitle() + "  "+ task.toString()+"  "+ task.getProject());
            }
            System.out.print("-> ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
            }

            if (option > 0 && option <= tasks.size()) {
                Task selectedTask = tasks.get(option - 1);
                optionsToWhatToEdit(selectedTask);
                break;
            } else {
                System.err.println("INVALID INPUT");
            }
        }
    }

    /**
     * Screens all optios to edit the chosen task.
     * @param chosenTask The task to edit.
     */
    private void optionsToWhatToEdit(Task chosenTask) {
        int option = 0;

        while (option != 4) {
            System.out.println();
            System.out.println("->Pick option->");
            System.out.println("-> (1) Update (Title, Due date or Project)");
            System.out.println("-> (2) Mark as done");
            System.out.println(".> (3) Remove task");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
            }

            switch (option) {
                case 1:
                    updateTask(chosenTask);
                    mainMenu();
                    break;
                case 2:
                    markAsDone(chosenTask);
                    mainMenu();
                    break;
                case 3:
                    RemoveTask(chosenTask);
                    mainMenu();
                    break;
                default:
                    System.err.println("INVALID INPUT");
                    break;
            }
            option = 0;
        }
    }

    /**
     * Updates all details about the task.
     * @param chosenTask The task to update.
     */
    private void updateTask(Task chosenTask) {
        System.out.println("Edit task");
        System.out.println("Current title: " + chosenTask.getTitle());
        System.out.print("Enter a new title->");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) {
            chosenTask.setTitle(newTitle);
        }

        boolean invalidDate = true;

        while (invalidDate) {
            System.out.println("Current due date: " + chosenTask.getDueDate());
            System.out.print("Enter a new due date [yyyy-mm-dd]->");
            String dueDateStr = scanner.nextLine();
            if (dueDateStr.isEmpty()) {
                break;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate newDueDate = LocalDate.parse(dueDateStr, formatter);
                chosenTask.setDueDate(newDueDate);
                invalidDate = false;
            } catch (Exception e) {
                System.err.println("INVALID INPUT");
            }
        }
        System.out.println("Current project: " + chosenTask.getProject());
        System.out.print("Enter a new project->");
        String newProject = scanner.nextLine();
        if (!newProject.isEmpty()) {
            chosenTask.setProject(newProject);
        }
    }

    /**
     * Marks the task as done.
     * @param chosenTask to mark as done.
     */
    private void markAsDone(Task chosenTask) {
        tasker.setAsDone(chosenTask);
        System.out.println("Task marked as DONE!");
    }

    /**
     * Removes the chosen task.
     * @param chosenTask to remove the task.
     */
    private void RemoveTask(Task chosenTask) {
        tasker.deleteTask(chosenTask);
        System.out.println("Task removed!");
    }

    /**
     * Save all tasks and quit.
     */
    private void saveAndExit() {
        fileHandler.writeAsData(tasker);
        scanner.close();
        System.out.println();
        System.out.println("Thank you for using ToDoLy");
        System.exit(0);
    }
}