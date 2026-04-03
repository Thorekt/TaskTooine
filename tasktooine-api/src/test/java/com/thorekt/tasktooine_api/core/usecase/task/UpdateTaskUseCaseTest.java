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

        UpdateTaskUseCase useCase = new UpdateTaskUseCase(
                repository,
                task.id,
                "New title",
                "New description");

        useCase.execute();

        assertEquals("New title", task.title);
        assertEquals("New description", task.description);
        assertSame(task, repository.updatedTask);
    }

    @Test
    void executeShouldNotUpdateFieldsWhenNull() {
        FakeTaskRepository repository = new FakeTaskRepository();
        Task task = new Task("task-1", "Old title", "Old description");
        repository.tasks.put(task.id, task);
        UpdateTaskUseCase useCase = new UpdateTaskUseCase(
                repository,
                task.id,
                null,
                null);

        useCase.execute();

        assertEquals("Old title", task.title);
        assertEquals("Old description", task.description);
        assertSame(task, repository.updatedTask);
    }

    @Test
    void executeShouldDoNothingWhenTaskDoesNotExist() {
        FakeTaskRepository repository = new FakeTaskRepository();

        UpdateTaskUseCase useCase = new UpdateTaskUseCase(
                repository,
                "missing-task",
                "New title",
                "New description");

        useCase.execute();

        assertNull(repository.updatedTask);
    }
}
