package tasks.model;

import java.util.Date;

public class TaskStub extends Task{
    private String title = "task";
    private Date start = new Date(2010, 5, 5);
    private Date end = new Date(2010, 5, 6);
    private int interval = 5;
    private boolean active = true;

    public TaskStub() {
        super("task", new Date(2010, 5, 5));
    }

    public String getTitle() {
        return title;
    }

    public boolean isActive() {
        return this.active;
    }

    public Date getStartTime() {
        return start;
    }

    public Date getEndTime() {
        return end;
    }

    public int getRepeatInterval() {
        return interval;
    }

    public boolean isRepeated() {
        return true;

    }
}
