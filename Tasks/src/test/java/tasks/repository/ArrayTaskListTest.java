package tasks.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.model.Task;
import tasks.model.TaskStub;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayTaskListTest {

    private static ArrayTaskList arrayTaskList;

    @BeforeAll
    static void setUp() {
        arrayTaskList = new ArrayTaskList();
    }

    @Test
    void add() {
        Task task = new TaskStub();
        arrayTaskList.add(task);
        assertEquals("task", arrayTaskList.getAll().get(0).getTitle());
    }

    @Test
    void remove() {
        Task task = new TaskStub();
        arrayTaskList.add(task);
        arrayTaskList.remove(task);
        assertEquals(0, arrayTaskList.getAll().size());
    }
}
