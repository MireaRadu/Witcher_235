package tasks.services;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import tasks.repository.ArrayTaskList;
import tasks.model.Task;
import tasks.repository.TaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TasksServiceTest {

    private static TasksService tasksService;
    private SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy, HH:mm:ss");

    @BeforeAll
    static void setUp() {
        TaskList taskList = new ArrayTaskList();
        tasksService = new TasksService(taskList);
    }

    @Test
    @Order(1)
    @Tag("ECP")
    @Timeout(1000)
    @DisplayName("Create Task Valid ECP")
    void createTaskValidECP() throws ParseException {
        Task task = tasksService.createTask("aaa", formatter.parse("Jan 3, 1998, 00:00:00"),
                formatter.parse("Feb 20, 2000, 12:00:00"), true, 3, true);
        assertEquals(task.getTitle(), "aaa");
        assertEquals(task.getStartTime().getTime(), formatter.parse("Jan 3, 1998, 00:00:00").getTime());
        assertEquals(task.getEndTime().getTime(), formatter.parse("Feb 20, 2000, 12:00:00").getTime());
        assertTrue(task.isRepeated());
        assertEquals(task.getRepeatInterval(), 3);
        assertTrue(task.isActive());
    }

    @Test
    @Order(2)
    @Tag("ECP")
    @DisplayName("Create Task Invalid interval (too small) ECP")
    void createTaskIntervalTooSmallECP() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> tasksService.createTask("aaa", formatter.parse("Jan 3, 1998, 00:00:00"),
                formatter.parse("Feb 20, 2000, 12:00:00"), true, -5, true));
        assertEquals(exception.getMessage(), "Interval should be > 1");
    }

    @Test
    @Order(3)
    @Tag("ECP")
    @DisplayName("Create Task Invalid interval (too big) ECP")
    void createTaskIntervalTooBigECP() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> tasksService.createTask("aaa", formatter.parse("Jan 3, 1998, 00:00:00"),
                formatter.parse("Feb 20, 2000, 12:00:00"), true, Integer.MAX_VALUE + 1, true));
        assertEquals(exception.getMessage(), "Interval should be > 1");
    }

    @Test
    @Order(4)
    @Tag("BVA")
    @Timeout(1000)
    @DisplayName("Create Task Valid BVA lower interval")
    void createTaskValidLowerBVA() throws ParseException {
        Task task = tasksService.createTask("aaa", new Date(1),
                formatter.parse("Feb 20, 2000, 12:00:00"), true, 3, true);
        assertEquals(task.getTitle(), "aaa");
        assertEquals(task.getStartTime().getTime(), new Date(1).getTime());
        assertEquals(task.getEndTime().getTime(), formatter.parse("Feb 20, 2000, 12:00:00").getTime());
        assertTrue(task.isRepeated());
        assertEquals(task.getRepeatInterval(), 3);
        assertTrue(task.isActive());
    }

    @Test
    @Order(5)
    @Tag("BVA")
    @Timeout(1000)
    @DisplayName("Create Task Valid BVA higher interval")
    void createTaskValidHigherBVA() {
        Task task = tasksService.createTask("aaa", new Date(Long.MAX_VALUE),
                new Date(Long.MAX_VALUE), true, Integer.MAX_VALUE, true);
        assertEquals(task.getTitle(), "aaa");
        assertEquals(task.getStartTime().getTime(), new Date(Long.MAX_VALUE).getTime());
        assertEquals(task.getEndTime().getTime(), new Date(Long.MAX_VALUE).getTime());
        assertTrue(task.isRepeated());
        assertEquals(task.getRepeatInterval(), Integer.MAX_VALUE);
        assertTrue(task.isActive());
    }

    @Test
    @Order(6)
    @Tag("BVA")
    @DisplayName("Create Task Invalid interval (too small) BVA")
    void createTaskIntervalTooSmallBVA() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> tasksService.createTask("aaa", formatter.parse("Jan 3, 1998, 00:00:00"),
                formatter.parse("Feb 20, 2000, 12:00:00"), true, 0, true));
        assertEquals(exception.getMessage(), "Interval should be > 1");
    }

    @Test
    @Order(7)
    @Tag("BVA")
    @DisplayName("Create Task Invalid interval (too big) BVA")
    void createTaskIntervalTooBigBVA() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> tasksService.createTask("aaa", formatter.parse("Jan 3, 1998, 00:00:00"),
                formatter.parse("Feb 20, 2000, 12:00:00"), true, Integer.MAX_VALUE + 1, true));
        assertEquals(exception.getMessage(), "Interval should be > 1");
    }

    @Test
    @Order(9)
    @Tag("BVA")
    @DisplayName("Create Task Invalid startDate (too small) BVA")
    void createTaskStartDateTooSmallBVA() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> tasksService.createTask("aaa", new Date(-1),
                formatter.parse("Feb 20, 2000, 12:00:00"), true, 5, true));
        assertEquals(exception.getMessage(), "Time cannot be negative");
    }

    @Test
    @Order(10)
    @Tag("BVA")
    @DisplayName("Create Task Invalid startDate (too big) BVA")
    void createTaskStartDateTooBigBVA() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> tasksService.createTask("aaa", new Date(Long.MAX_VALUE + 1),
                new Date(Long.MAX_VALUE + 1), true, 5, true));
        assertEquals(exception.getMessage(), "Time cannot be negative");
    }

}
