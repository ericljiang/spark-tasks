package model;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskDao {
    
    /**
     * Retrieves all tasks and subtasks in a tree structure.
     */
    List<Task> getAllTasks();
    
    /**
     * Updates the specified task's name.
     */
    void updateTaskName(int id, String name);
    
    /**
     * Updates the specified task's duration.
     */
    void updateTaskHours(int id, float hours);
    
    /**
     * Updates the specified task's due date.
     */
    void updateTaskDueDate(int id, LocalDateTime dueDate);
    
    /**
     * Creates a new root level task.
     * Returns the id of the new task.
     */
    int createParentTask();
    
    /**
     * Creates a new subtask under the specified parent task.
     * Returns the id of the new task.
     */
    int createSubtask(int parentId);
    
    /**
     * Delete the task with the specified id.
     */
    void deleteTask(int id);
}
