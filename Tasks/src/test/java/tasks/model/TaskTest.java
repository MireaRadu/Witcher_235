package tasks.model;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TaskTest {

    @Test
    void nextTimeAfterF02_TC01() {
        Date current = new Date(2001, Calendar.JANUARY, 1, 0, 2, 0);
        Date startDate = new Date(2001, Calendar.JANUARY, 1, 0, 1, 0);
        Date endDate = new Date(2001, Calendar.JANUARY, 1, 0, 1, 0);
        Task task = new Task("aaa", startDate, endDate,1);
        Date date = task.getNextTimeAfterForRepeated(current);
        assertNull(date);
    }

    @Test
    void nextTimeAfterF02_TC02() {
        Date current = new Date(2001, Calendar.JANUARY, 1, 0, 0, 0);
        Date startDate = new Date(2001, Calendar.JANUARY, 1, 0, 1, 0);
        Date endDate = new Date(2001, Calendar.JANUARY, 1, 0, 1, 0);
        Task task = new Task("aaa", startDate, endDate,1);
        Date date = task.getNextTimeAfterForRepeated(current);
        assertNull(date);
    }

    @Test
    void nextTimeAfterF02_TC03() {
        Date current = new Date(2001, Calendar.JANUARY, 1, 0, 1, 0);
        Date startDate = new Date(2001, Calendar.JANUARY, 1, 0, 2, 0);
        Date endDate = new Date(2001, Calendar.JANUARY, 1, 0, 0, 0);
        Task task = new Task("aaa", startDate, endDate,1);
        Date date = task.getNextTimeAfterForRepeated(current);
        assertNull(date);
    }

    @Test
    void nextTimeAfterF02_TC04() {
        Date current = new Date(2001, Calendar.JANUARY, 1, 0, 0, 0);
        Date startDate = new Date(2001, Calendar.JANUARY, 1, 0, 0, 0);
        Date endDate = new Date(2001, Calendar.JANUARY, 1, 0, 0, 0);
        Task task = new Task("aaa", startDate, endDate,1);
        Date date = task.getNextTimeAfterForRepeated(current);

        Date expectedDate = new Date(2001, Calendar.JANUARY, 1, 0, 0, 1);
        assertEquals(expectedDate, date);
    }
    @Test

    void nextTimeAfterF02_TC05() {
        Date current = new Date(2001, Calendar.JANUARY, 1, 0, 0, 1);
        Date startDate = new Date(2001, Calendar.JANUARY, 1, 0, 0, 0);
        Date endDate = new Date(2001, Calendar.JANUARY, 1, 0, 0, 4);
        Task task = new Task("aaa", startDate, endDate,2);
        Date date = task.getNextTimeAfterForRepeated(current);

        Date expectedDate = new Date(2001, Calendar.JANUARY, 1, 0, 0, 2);
        assertEquals(expectedDate, date);
    }@Test

    void nextTimeAfterF02_TC06() {
        Date current = new Date(2001, Calendar.JANUARY, 1, 1, 2, 0);
        Date startDate = new Date(2001, Calendar.JANUARY, 1, 0, 0, 0);
        Date endDate = new Date(2001, Calendar.JANUARY, 1, 0, 0, 1);
        Task task = new Task("aaa", startDate, endDate,2);
        Date date = task.getNextTimeAfterForRepeated(current);
        assertNull(date);
    }
}
