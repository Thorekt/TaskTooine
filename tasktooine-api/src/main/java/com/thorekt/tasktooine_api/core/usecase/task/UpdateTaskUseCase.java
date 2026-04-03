package com.thorekt.tasktooine_api.core.usecase.task;

import com.thorekt.tasktooine_api.core.entity.Task;
import com.thorekt.tasktooine_api.core.usecase.IUseCase;

public class UpdateTaskUseCase implements IUseCase {
    private final ITaskRepository taskRepository;
    private final String taskId;
    private final String title;
    private final String description;

    public UpdateTaskUseCase(ITaskRepository taskRepository, String taskId, String title, String description) {
        this.taskRepository = taskRepository;
        this.taskId = taskId;
        this.title = title;
        this.description = description;
    }

    @Override
    public void execute() {
        Task task = taskRepository.getTaskById(taskId);

        if (task == null) {
            return;
        }

        if (title != null) {
            task.title = title;
        }

        if (description != null) {
            task.description = description;
        }

        taskRepository.updateTask(task);
    }
}
