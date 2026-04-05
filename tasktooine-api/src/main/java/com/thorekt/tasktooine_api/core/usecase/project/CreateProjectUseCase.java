package com.thorekt.tasktooine_api.core.usecase.project;

import com.thorekt.tasktooine_api.core.entity.Project;
import com.thorekt.tasktooine_api.core.usecase.IUseCase;

/**
 * Use case responsible for creating a project.
 *
 * @author Thorekt
 */
public class CreateProjectUseCase implements IUseCase {
    private final IProjectRepository projectRepository;
    private final Project project;

    public CreateProjectUseCase(IProjectRepository projectRepository, Project project) {
        this.projectRepository = projectRepository;
        this.project = project;
    }

    @Override
    public void execute() {
        projectRepository.createProject(project);
    }
}
