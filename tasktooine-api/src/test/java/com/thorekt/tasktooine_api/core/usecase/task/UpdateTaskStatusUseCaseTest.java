package com.thorekt.tasktooine_api.core.usecase.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.thorekt.tasktooine_api.core.entity.Task;
import com.thorekt.tasktooine_api.core.entity.TaskStatus;

public class UpdateTaskStatusUseCaseTest {
    @Test
    void executeShouldUpdateTaskStatus() {
        FakeTaskRepository repository = new FakeTaskRepository();
        Task task = new Task("task-1", "Task 1", "Description");
        repository.tasks.put(task.id, task);

        UpdateTaskStatusUseCase useCase = new UpdateTaskStatusUseCase(repository, task.id, TaskStatus.DONE);

        useCase.execute();

        assertEquals(TaskStatus.DONE, task.status);
        assertSame(task, repository.updatedTask);
    }

    @Test
    void executeShouldDoNothingWhenTaskDoesNotExist() {
        FakeTaskRepository repository = new FakeTaskRepository();

        UpdateTaskStatusUseCase useCase = new UpdateTaskStatusUseCase(repository, "missing-task", TaskStatus.DONE);

        useCase.execute();

        assertNull(repository.updatedTask);
    }
}
