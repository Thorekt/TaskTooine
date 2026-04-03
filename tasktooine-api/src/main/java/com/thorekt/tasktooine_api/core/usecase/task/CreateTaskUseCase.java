package com.thorekt.tasktooine_api.core.usecase.task;

import com.thorekt.tasktooine_api.core.entity.Task;
import com.thorekt.tasktooine_api.core.usecase.IUseCase;

public class CreateTaskUseCase implements IUseCase {
    private final ITaskRepository taskRepository;
    private final Task task;

    public CreateTaskUseCase(ITaskRepository taskRepository, Task task) {
        this.taskRepository = taskRepository;
        this.task = task;
    }

    @Override
    public void execute() {
        taskRepository.createTask(task);
    }
}
