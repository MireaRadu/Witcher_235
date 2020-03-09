package tasks.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import org.controlsfx.control.Notifications;
import tasks.model.Task;

import java.util.Date;

public class Notificator extends Thread {

    private static final int MILLISECONDS_IN_SEC = 1000;
    private static final int SECONDS_IN_MIN = 60;

    private static final Logger log = Logger.getLogger(Notificator.class.getName());

    private ObservableList<Task> tasksList;

    public Notificator(ObservableList<Task> tasksList){
        this.tasksList=tasksList;
    }

    @Override
    public void run() {
        while (true) {
            Date currentDate = new Date();
            tasksList.stream().filter(Task::isActive).forEach(task -> {
                    if (task.isRepeated() && task.getEndTime().after(currentDate)){
                        Date next = task.nextTimeAfter(currentDate);
                        long currentMinute = getTimeInMinutes(currentDate);
                        long taskMinute = getTimeInMinutes(next);
                        if (currentMinute == taskMinute){
                            showNotification(task);
                        }
                    }
                    else if (!task.isRepeated() && getTimeInMinutes(currentDate) == getTimeInMinutes(task.getStartTime())){
                                showNotification(task);
                        }
            });
            try {
                Thread.sleep((long)MILLISECONDS_IN_SEC * SECONDS_IN_MIN);
            } catch (InterruptedException e) {
                log.error("thread interrupted exception");
                Thread.currentThread().interrupt();
            }
        }
    }
    public static void showNotification(Task task){
        log.info("push notification showing");
        Platform.runLater(() ->
            Notifications.create().title("Task reminder").text("It's time for " + task.getTitle()).showInformation()
        );
    }
    private static long getTimeInMinutes(Date date){
        return date.getTime()/ MILLISECONDS_IN_SEC / SECONDS_IN_MIN;
    }
}
