package com.thorekt.tasktooine_api.core.usecase.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.thorekt.tasktooine_api.core.entity.Project;

public class CreateProjectUseCaseTest {
    @Test
    void executeShouldCreateProject() {
        FakeProjectRepository repository = new FakeProjectRepository();
        Project project = new Project("project-1", "Project 1", "Description");

        CreateProjectUseCase useCase = new CreateProjectUseCase(repository, project);

        useCase.execute();

        assertSame(project, repository.createdProject);
        assertEquals(project, repository.getProjectById("project-1"));
    }
}
