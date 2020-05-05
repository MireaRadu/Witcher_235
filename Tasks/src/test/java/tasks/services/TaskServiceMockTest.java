package tasks.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.model.Task;
import tasks.repository.ArrayTaskList;

import java.util.Date;

import static org.mockito.Mockito.times;

public class TaskServiceMockTest {

    private TasksService tasksService;
    private ArrayTaskList arrayTaskList;

    @BeforeEach
    void setUp() {
        arrayTaskList = Mockito.mock(ArrayTaskList.class);
        tasksService = new TasksService(arrayTaskList);
    }

    @Test
    void addTaskMock() {
        Task task = new Task("task", new Date());
        Mockito.doNothing().when(arrayTaskList).add(task);
        tasksService.addTask(task);
        Mockito.verify(arrayTaskList, times(1)).add(task);
    }

    @Test
    void updateTaskMock() {
        Task task = new Task("task", new Date());
        tasksService.addTask(task);
        Mockito.doReturn(true).when(arrayTaskList).remove(task);
        Mockito.doNothing().when(arrayTaskList).add(task);
        tasksService.updateTask(task, task);
        Mockito.verify(arrayTaskList, times(2)).add(task);
        Mockito.verify(arrayTaskList, times(1)).remove(task);
    }
}
