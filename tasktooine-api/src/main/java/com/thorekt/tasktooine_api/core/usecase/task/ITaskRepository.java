package com.thorekt.tasktooine_api.core.usecase.task;

import com.thorekt.tasktooine_api.core.entity.Task;

/**
 * Repository contract used by task-related use cases.
 *
 * @author Thorekt
 */
public interface ITaskRepository {
    public void createTask(Task task);

    public Task getTaskById(String id);

    public void updateTask(Task task);

    public void deleteTask(String id);
}
