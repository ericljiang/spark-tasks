package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoDummy implements TaskDao {

    @Override
    public List<Task> getAllTasks() {
        Task task = new Task(1);
        task.setName("foo");
        task.setHours(1.5f);
        task.setDueDate(LocalDateTime.now());
        task.setRoot(true);
        List<Task> list = new ArrayList<Task>();
        list.add(task);
        return list;
    }

    @Override
    public void updateTaskName(int id, String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTaskHours(int id, float hours) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateTaskDueDate(int id, LocalDateTime dueDate) {
        // TODO Auto-generated method stub

    }

    @Override
    public int createParentTask() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int createSubtask(int parentId) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteTask(int id) {
        // TODO Auto-generated method stub

    }

}
