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

        Project updatedProject = new Project(project.id, "New name", "New description");

        UpdateProjectUseCase useCase = new UpdateProjectUseCase(
                repository,
                updatedProject);

        useCase.execute();

        assertSame(updatedProject, repository.updatedProject);
        assertEquals("New name", repository.projects.get(project.id).name);
        assertEquals("New description", repository.projects.get(project.id).description);
    }

    @Test
    void executeShouldDoNothingWhenProjectDoesNotExist() {
        FakeProjectRepository repository = new FakeProjectRepository();

        Project updatedProject = new Project("missing-project", "New name", "New description");

        UpdateProjectUseCase useCase = new UpdateProjectUseCase(
                repository,
                updatedProject);

        useCase.execute();

        assertNull(repository.updatedProject);
    }
}
