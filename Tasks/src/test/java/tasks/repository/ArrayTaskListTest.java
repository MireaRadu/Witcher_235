package tasks.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.model.Task;

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
        Task task = new Task("task", new Date());
        arrayTaskList.add(task);
        assertEquals("task", arrayTaskList.getAll().get(0).getTitle());
    }

    @Test
    void remove() {
        Task task = new Task("task", new Date());
        arrayTaskList.add(task);
        arrayTaskList.remove(task);
        assertEquals(0, arrayTaskList.getAll().size());
    }
}
