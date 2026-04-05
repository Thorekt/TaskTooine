package com.thorekt.tasktooine_api.core.usecase.project;

import com.thorekt.tasktooine_api.core.usecase.IUseCase;

/**
 * Use case responsible for deleting a project.
 *
 * @author Thorekt
 */
public class DeleteProjectUseCase implements IUseCase {
    private final IProjectRepository projectRepository;
    private final String projectId;

    public DeleteProjectUseCase(IProjectRepository projectRepository, String projectId) {
        this.projectRepository = projectRepository;
        this.projectId = projectId;
    }

    @Override
    public void execute() {
        projectRepository.deleteProject(projectId);
    }
}
