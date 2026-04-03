package com.thorekt.tasktooine_api.core.usecase.task;

import java.util.HashMap;
import java.util.Map;

import com.thorekt.tasktooine_api.core.entity.Task;

public class FakeTaskRepository implements ITaskRepository {
    public final Map<String, Task> tasks = new HashMap<>();
    public Task createdTask;
    public Task updatedTask;
    public String deletedTaskId;

    @Override
    public void createTask(Task task) {
        createdTask = task;
        tasks.put(task.id, task);
    }

    @Override
    public Task getTaskById(String id) {
        return tasks.get(id);
    }

    @Override
    public void updateTask(Task task) {
        updatedTask = task;
        tasks.put(task.id, task);
    }

    @Override
    public void deleteTask(String id) {
        deletedTaskId = id;
        tasks.remove(id);
    }
}
