package com.thorekt.tasktooine_api.core.usecase.task;

import com.thorekt.tasktooine_api.core.entity.Task;
import com.thorekt.tasktooine_api.core.entity.TaskStatus;
import com.thorekt.tasktooine_api.core.usecase.IUseCase;

/**
 * Use case responsible for updating the status of an existing task.
 *
 * @author Thorekt
 */
public class UpdateTaskStatusUseCase implements IUseCase {
    private final ITaskRepository taskRepository;
    private final String taskId;
    private final TaskStatus status;

    public UpdateTaskStatusUseCase(ITaskRepository taskRepository, String taskId, TaskStatus status) {
        this.taskRepository = taskRepository;
        this.taskId = taskId;
        this.status = status;
    }

    @Override
    public void execute() {
        Task task = taskRepository.getTaskById(taskId);

        if (task == null) {
            return;
        }

        task.status = status;
        taskRepository.updateTask(task);
    }
}
