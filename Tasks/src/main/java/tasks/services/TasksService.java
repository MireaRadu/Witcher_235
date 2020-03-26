package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tasks.model.Task;
import tasks.model.TaskList;
import tasks.model.TasksOperations;

import java.util.Date;

public class TasksService {

    private TaskList tasks;

    public TasksService(TaskList tasks){
        this.tasks = tasks;
    }

    public ObservableList<Task> getObservableList(){
        return FXCollections.observableArrayList(tasks.getAll());
    }
    public String getIntervalInHours(Task task){
        int seconds = task.getRepeatInterval();
        int minutes = seconds / DateService.SECONDS_IN_MINUTE;
        int hours = minutes / DateService.MINUTES_IN_HOUR;
        minutes = minutes % DateService.MINUTES_IN_HOUR;
        return formTimeUnit(hours) + ":" + formTimeUnit(minutes);//hh:MM
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void updateTask(Task oldTask, Task newTask){
        tasks.remove(oldTask);
        tasks.add(newTask);
    }

    public Task createTask(String title, Date startDate, Date endDate, boolean repetitive, int interval, boolean active){
        Task task;
        if(repetitive){
            if (startDate.after(endDate)) throw new IllegalArgumentException("Start date should be before end");
            task = new Task(title, startDate, endDate, interval);
        }else{
            task = new Task(title, startDate);
        }
        task.setActive(active);
        return task;
    }

    public String formTimeUnit(int timeUnit){
        StringBuilder sb = new StringBuilder();
        if (timeUnit < 10) sb.append("0");
        if (timeUnit == 0) sb.append("0");
        else {
            sb.append(timeUnit);
        }
        return sb.toString();
    }
    public int parseFromStringToSeconds(String stringTime){//hh:MM
        String[] units = stringTime.split(":");
        int hours = Integer.parseInt(units[0]);
        int minutes = Integer.parseInt(units[1]);
        return (hours * DateService.MINUTES_IN_HOUR + minutes) * DateService.SECONDS_IN_MINUTE;
    }

    public Iterable<Task> filterTasks(Date start, Date end){
        TasksOperations tasksOps = new TasksOperations(getObservableList());
        return tasksOps.incoming(start,end);
    }
}
