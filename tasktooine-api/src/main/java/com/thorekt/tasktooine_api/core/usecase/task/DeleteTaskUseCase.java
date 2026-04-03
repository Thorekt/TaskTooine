package com.thorekt.tasktooine_api.core.usecase.task;

import com.thorekt.tasktooine_api.core.usecase.IUseCase;

public class DeleteTaskUseCase implements IUseCase {
    private final ITaskRepository taskRepository;
    private final String taskId;

    public DeleteTaskUseCase(ITaskRepository taskRepository, String taskId) {
        this.taskRepository = taskRepository;
        this.taskId = taskId;
    }

    @Override
    public void execute() {
        taskRepository.deleteTask(taskId);
    }
}
