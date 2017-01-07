package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskDaoSQL implements TaskDao {
    private Sql2o sql2o;
    
    public TaskDaoSQL(Sql2o sql2o) {
        this.sql2o = sql2o;
        log.info("Data access object created.");
    }

    @Override
    public List<Task> getAllTasks() {
        log.info("Getting all tasks from database.");
        String sql =
                "SELECT * " +
                "FROM Task " +
                "WHERE root = true";

        try (Connection con = sql2o.open()) {
            List<Task> tasks = con.createQuery(sql)
                    .addColumnMapping("due_date", "dueDate")
                    .executeAndFetch(Task.class);
            tasks.forEach(t -> populateSubtasks(t));
            return tasks;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ArrayList<Task>();
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

    private void populateSubtasks(Task task) {
        int id = task.getId();
        log.info("Getting subtasks for task " + id + ".");
        String sql =
                "SELECT Task.* " +
                "FROM Subtask, Task " +
                "WHERE Subtask.child_id = Task.id " +
                "AND Subtask.parent_id = :parentId";
        try (Connection con = sql2o.open()) {
            List<Task> subtasks = con.createQuery(sql)
                .addParameter("parentId", id)
                .addColumnMapping("due_date", "dueDate")
                .executeAndFetch(Task.class);
            subtasks.forEach(t -> populateSubtasks(t));
            task.setSubtasks(subtasks);
            log.debug("Added " + subtasks.size() + " subtasks to task " + id);
        }
    }
}
