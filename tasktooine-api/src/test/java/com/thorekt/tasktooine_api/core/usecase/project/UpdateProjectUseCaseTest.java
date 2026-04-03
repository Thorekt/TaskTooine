package com.thorekt.tasktooine_api.core.usecase.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.thorekt.tasktooine_api.core.entity.Project;

public class UpdateProjectUseCaseTest {
    @Test
    void executeShouldUpdateProjectFields() {
        FakeProjectRepository repository = new FakeProjectRepository();
        Project project = new Project("project-1", "Old name", "Old description");
        repository.projects.put(project.id, project);

        UpdateProjectUseCase useCase = new UpdateProjectUseCase(
                repository,
                project.id,
                "New name",
                "New description");

        useCase.execute();

        assertEquals("New name", project.name);
        assertEquals("New description", project.description);
        assertSame(project, repository.updatedProject);
    }

    @Test
    void executeShouldNotUpdateFieldsWhenNull() {
        FakeProjectRepository repository = new FakeProjectRepository();
        Project project = new Project("project-1", "Old name", "Old description");
        repository.projects.put(project.id, project);

        UpdateProjectUseCase useCase = new UpdateProjectUseCase(
                repository,
                project.id,
                null,
                null);

        useCase.execute();

        assertEquals("Old name", project.name);
        assertEquals("Old description", project.description);
        assertSame(project, repository.updatedProject);
    }

    @Test
    void executeShouldDoNothingWhenProjectDoesNotExist() {
        FakeProjectRepository repository = new FakeProjectRepository();

        UpdateProjectUseCase useCase = new UpdateProjectUseCase(
                repository,
                "missing-project",
                "New name",
                "New description");

        useCase.execute();

        assertNull(repository.updatedProject);
    }
}
