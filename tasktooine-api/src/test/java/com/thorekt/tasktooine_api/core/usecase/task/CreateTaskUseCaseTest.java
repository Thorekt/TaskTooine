package com.thorekt.tasktooine_api.core.usecase.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.thorekt.tasktooine_api.core.entity.Task;

public class CreateTaskUseCaseTest {
    @Test
    void executeShouldCreateTask() {
        FakeTaskRepository repository = new FakeTaskRepository();
        Task task = new Task("task-1", "Task 1", "Description");

        CreateTaskUseCase useCase = new CreateTaskUseCase(repository, task);

        useCase.execute();

        assertSame(task, repository.createdTask);
        assertEquals(task, repository.getTaskById("task-1"));
    }
}
