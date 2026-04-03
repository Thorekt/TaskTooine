package com.thorekt.tasktooine_api.core.usecase.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.thorekt.tasktooine_api.core.entity.Project;
import com.thorekt.tasktooine_api.core.entity.Task;

public class AddTaskToProjectUseCaseTest {
    @Test
    void executeShouldAddTaskToProject() {
        FakeProjectRepository repository = new FakeProjectRepository();
        Project project = new Project("project-1", "Project 1", "Description");
        Task task = new Task("task-1", "Task 1", "Task description");
        repository.projects.put(project.id, project);

        AddTaskToProjectUseCase useCase = new AddTaskToProjectUseCase(repository, project.id, task);

        useCase.execute();

        assertEquals(1, project.tasks.size());
        assertSame(task, project.tasks.getFirst());
        assertSame(project, repository.updatedProject);
    }

    @Test
    void executeShouldDoNothingWhenProjectDoesNotExist() {
        FakeProjectRepository repository = new FakeProjectRepository();
        Task task = new Task("task-1", "Task 1", "Task description");

        AddTaskToProjectUseCase useCase = new AddTaskToProjectUseCase(repository, "missing-project", task);

        useCase.execute();

        assertNull(repository.updatedProject);
    }
}
