package com.SDAprojects.ToDoLy;

import com.SDAproject.ToDoLy.Task;
import com.SDAproject.ToDoLy.Tasker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskerTest {
    @Test
    public void testCreateTaskToTaskList() {
        Tasker tasker = new Tasker();
        Task task = new Task(null,null, null, null);
        tasker.createTask(task);
        assertEquals(1,1,tasker.getSize());
    }

    @Test
    public void testDeleteTaskFromTaskList(){
        Tasker tasker = new Tasker();
        Task task = new Task(null, null, null, null);
        tasker.createTask(task);
        tasker.deleteTask(task);
        assertEquals(0, 0, tasker.getSize());
    }
}
