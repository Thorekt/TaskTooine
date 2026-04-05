package com.thorekt.tasktooine_api.core.usecase.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.thorekt.tasktooine_api.core.entity.Task;

public class UpdateTaskUseCaseTest {
    @Test
    void executeShouldUpdateTaskFields() {
        FakeTaskRepository repository = new FakeTaskRepository();
        Task task = new Task("task-1", "Old title", "Old description");
        repository.tasks.put(task.id, task);

        Task updatedTask = new Task(task.id, "New title", "New description");

        UpdateTaskUseCase useCase = new UpdateTaskUseCase(
                repository,
                updatedTask);

        useCase.execute();

        assertSame(updatedTask, repository.updatedTask);
        assertEquals("New title", repository.tasks.get(task.id).title);
        assertEquals("New description", repository.tasks.get(task.id).description);
    }

    @Test
    void executeShouldDoNothingWhenTaskDoesNotExist() {
        FakeTaskRepository repository = new FakeTaskRepository();

        Task updatedTask = new Task("missing-task", "New title", "New description");

        UpdateTaskUseCase useCase = new UpdateTaskUseCase(
                repository,
                updatedTask);

        useCase.execute();

        assertNull(repository.updatedTask);
    }
}
