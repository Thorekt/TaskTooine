package com.thorekt.tasktooine_api.core.usecase.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.thorekt.tasktooine_api.core.entity.Project;
import com.thorekt.tasktooine_api.core.entity.Task;

public class RemoveTaskFromProjectUseCaseTest {
    @Test
    void executeShouldRemoveTaskFromProject() {
        FakeProjectRepository repository = new FakeProjectRepository();
        Task task = new Task("task-1", "Task 1", "Task description");
        Project project = new Project("project-1", "Project 1", "Description");
        project.tasks.add(task);
        repository.projects.put(project.id, project);

        RemoveTaskFromProjectUseCase useCase = new RemoveTaskFromProjectUseCase(repository, project.id, task.id);

        useCase.execute();

        assertEquals(0, project.tasks.size());
        assertSame(project, repository.updatedProject);
    }

    @Test
    void executeShouldDoNothingWhenProjectDoesNotExist() {
        FakeProjectRepository repository = new FakeProjectRepository();
        RemoveTaskFromProjectUseCase useCase = new RemoveTaskFromProjectUseCase(repository, "missing-project",
                "task-1");

        useCase.execute();

        assertNull(repository.updatedProject);
    }

    @Test
    void executeShouldDoNothingWhenTaskDoesNotExist() {
        FakeProjectRepository repository = new FakeProjectRepository();
        Project project = new Project("project-1", "Project 1", "Description");
        repository.projects.put(project.id, project);

        RemoveTaskFromProjectUseCase useCase = new RemoveTaskFromProjectUseCase(repository, project.id, "missing-task");

        useCase.execute();

        assertNull(repository.updatedProject);
    }
}
