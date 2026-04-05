package com.thorekt.tasktooine_api.adapter.controller;

import com.thorekt.tasktooine_api.adapter.dto.TaskRecord;
import com.thorekt.tasktooine_api.core.entity.Task;
import com.thorekt.tasktooine_api.core.entity.TaskStatus;
import com.thorekt.tasktooine_api.core.usecase.task.CreateTaskUseCase;
import com.thorekt.tasktooine_api.core.usecase.task.DeleteTaskUseCase;
import com.thorekt.tasktooine_api.core.usecase.task.ITaskRepository;
import com.thorekt.tasktooine_api.core.usecase.task.UpdateTaskStatusUseCase;
import com.thorekt.tasktooine_api.core.usecase.task.UpdateTaskUseCase;

/**
 * Application controller dedicated to task operations.
 * This controller orchestrates task-related use cases and exposes DTO-based
 * methods for the outer layers of the application.
 * 
 * @author Thorekt
 */
public class TaskController {
    private final ITaskRepository taskRepository;

    /**
     * Creates a task controller with the repository required by the use cases.
     *
     * @param taskRepository repository used to load and persist tasks
     */
    public TaskController(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Creates a new task from the provided DTO and returns the persisted task.
     *
     * @param taskRecord task data to create
     * @return the created task, or {@code null} if it cannot be retrieved
     */
    public TaskRecord createTask(TaskRecord taskRecord) {
        new CreateTaskUseCase(taskRepository, taskRecord.toEntity()).execute();
        return getTaskById(taskRecord.id());
    }

    /**
     * Retrieves a task by its identifier.
     *
     * @param taskId identifier of the task to retrieve
     * @return the matching task DTO, or {@code null} if no task is found
     */
    public TaskRecord getTaskById(String taskId) {
        Task task = taskRepository.getTaskById(taskId);

        if (task == null) {
            return null;
        }

        return TaskRecord.fromEntity(task);
    }

    /**
     * Updates the title and description of an existing task.
     *
     * @param taskId     identifier of the task to update
     * @param taskRecord DTO carrying the updated values
     * @return the updated task DTO, or {@code null} if no task is found
     */
    public TaskRecord updateTask(String taskId, TaskRecord taskRecord) {
        Task task = taskRecord.toEntity();
        task.id = taskId;

        new UpdateTaskUseCase(taskRepository, task).execute();

        return getTaskById(taskId);
    }

    /**
     * Updates the status of an existing task.
     *
     * @param taskId identifier of the task to update
     * @param status new task status as an enum name
     * @return the updated task DTO, or {@code null} if no task is found
     */
    public TaskRecord updateTaskStatus(String taskId, String status) {
        new UpdateTaskStatusUseCase(
                taskRepository,
                taskId,
                TaskStatus.valueOf(status)).execute();

        return getTaskById(taskId);
    }

    /**
     * Deletes a task by its identifier.
     *
     * @param taskId identifier of the task to delete
     */
    public void deleteTask(String taskId) {
        new DeleteTaskUseCase(taskRepository, taskId).execute();
    }
}
