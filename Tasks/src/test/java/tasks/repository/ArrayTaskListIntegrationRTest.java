package tasks.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.model.Task;
import tasks.model.TaskStub;
import tasks.services.TasksService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayTaskListIntegrationRTest {
    private static ArrayTaskList arrayTaskList;
    private static TasksService tasksService;

    @BeforeAll
    static void setUp() {
        Task task1 = new TaskStub();
        Task task2 = new TaskStub();
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        arrayTaskList = new ArrayTaskList(tasks);
        tasksService = new TasksService(arrayTaskList);
    }

    @Test
    void getObservableList() {
        assertEquals(2, tasksService.getObservableList().size());
    }

    @Test
    void add() {
        Task task1 = new TaskStub();
        tasksService.addTask(task1);
        assertEquals(3, arrayTaskList.getAll().size());
    }
}
