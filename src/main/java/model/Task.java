package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Task {
    private int id;
    private String name;
    private double hours;
    private LocalDateTime dueDate;
    private boolean root;
    private List<Task> subtasks;
    
    public Task(int id) {
        this.id = id;
        this.subtasks = new ArrayList<Task>();
    }
    
    public void addSubtask(Task task) {
        subtasks.add(task);
    }
}
