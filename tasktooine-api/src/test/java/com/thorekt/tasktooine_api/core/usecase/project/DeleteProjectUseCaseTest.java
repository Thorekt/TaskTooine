package com.thorekt.tasktooine_api.core.usecase.project;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.thorekt.tasktooine_api.core.entity.Project;

public class DeleteProjectUseCaseTest {
    @Test
    void executeShouldDeleteProject() {
        FakeProjectRepository repository = new FakeProjectRepository();
        Project project = new Project("project-1", "Project 1", "Description");
        repository.projects.put(project.id, project);

        DeleteProjectUseCase useCase = new DeleteProjectUseCase(repository, project.id);

        useCase.execute();

        assertNull(repository.getProjectById(project.id));
    }
}
