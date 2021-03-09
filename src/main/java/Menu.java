import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private boolean loopIsTrue = true;
    private Tasker tasker = new Tasker();


    public void displayUserMenu()
    {
        while (loopIsTrue)
        {
            System.out.println("Welcome to ToDoLy");
            System.out.println("Pick an option:");
            System.out.println("(1) Add New Task");
            System.out.println("(2) Show My List");
            System.out.println("(3) Edit Task (Delete) ");
            System.out.println("(4) Exit");
            String number = input.nextLine();
            getOptionsOfMenu(Integer.parseInt(number));
        }
    }
    private void getOptionsOfMenu(int option)
    {
        if (option >= 1 && option <= 4)
        {
            switch (option)
            {
                case 1:
                    System.out.println("Write down your task.");
                    String title = input.nextLine();
                    System.out.println("Write down your task date.");
                    String date = input.nextLine();
                    tasker.createTask(new Task(title,date));
                    System.out.println("----------------");
                    System.out.println("----------------");
                    System.out.println("Task was added.");
                    System.out.println("----------------");
                    System.out.println("----------------");
                    break;
                case 2:
                    System.out.println("----------------");
                    System.out.println("----------------");
                    System.out.println("----------------");
                    System.out.println("----------------");
                    System.out.println("TO DO LIST:");
                    tasker.showTasks();
                    System.out.println("----------------");
                    System.out.println("----------------");
                    System.out.println("----------------");
                    System.out.println("----------------");
                    break;
                case 3:
                    System.out.println("Write down your task that you want to delete from task list.");
                    Scanner delete_input = new Scanner(System.in);
                    String taskName = delete_input.nextLine();
                    tasker.deleteTask(taskName);
                    System.out.println("----------------");
                    System.out.println("----------------");
                    System.out.println("Task is deleted");
                    System.out.println("----------------");
                    System.out.println("----------------");
                    break;
                case 4:
                    loopIsTrue = false;
                    break;

            }
        }
    }



}
