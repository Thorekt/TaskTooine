package com.thorekt.tasktooine_api.core.usecase.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.thorekt.tasktooine_api.core.entity.Task;

public class DeleteTaskUseCaseTest {
    @Test
    void executeShouldDeleteTask() {
        FakeTaskRepository repository = new FakeTaskRepository();
        Task task = new Task("task-1", "Task 1", "Description");
        repository.tasks.put(task.id, task);

        DeleteTaskUseCase useCase = new DeleteTaskUseCase(repository, task.id);

        useCase.execute();

        assertEquals(task.id, repository.deletedTaskId);
        assertNull(repository.getTaskById(task.id));
    }
}
