package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.Task;
import tasks.repository.ArrayTaskList;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskServiceIntegrationETest {
    private TasksService tasksService;
    private ArrayTaskList arrayTaskList;

    @BeforeEach
    void setUp() {
        arrayTaskList = new ArrayTaskList();
        tasksService = new TasksService(arrayTaskList);
    }

    @Test
    void addTask() {
        Task task = new Task("task", new Date());
        tasksService.addTask(task);
        assertEquals(1, arrayTaskList.getAll().size());
    }

    @Test
    void updateTask() {
        Task task1 = new Task("task", new Date());
        Task task2 = new Task("task2", new Date());
        arrayTaskList.add(task1);
        tasksService.updateTask(task1, task2);
        assertEquals("task2", arrayTaskList.getAll().get(0).getTitle());
    }
}
