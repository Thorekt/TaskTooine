package com.thorekt.tasktooine_api.core.usecase.task;

import com.thorekt.tasktooine_api.core.entity.Task;
import com.thorekt.tasktooine_api.core.usecase.IUseCase;

public class UpdateTaskUseCase implements IUseCase {
    private final ITaskRepository taskRepository;
    private final Task task;

    /**
     * Creates an update task use case.
     *
     * @param taskRepository repository used to load and persist tasks
     * @param task           task carrying the updated state
     */
    public UpdateTaskUseCase(ITaskRepository taskRepository, Task task) {
        this.taskRepository = taskRepository;
        this.task = task;
    }

    /**
     * Updates an existing task if it exists in the repository.
     */
    @Override
    public void execute() {
        Task existingTask = taskRepository.getTaskById(task.id);

        if (existingTask == null) {
            return;
        }

        taskRepository.updateTask(task);
    }
}
