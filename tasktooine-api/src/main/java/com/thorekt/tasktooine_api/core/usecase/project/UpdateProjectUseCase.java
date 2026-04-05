package com.thorekt.tasktooine_api.core.usecase.project;

import com.thorekt.tasktooine_api.core.entity.Project;
import com.thorekt.tasktooine_api.core.usecase.IUseCase;

/**
 * Use case responsible for updating an existing project.
 *
 * @author Thorekt
 */
public class UpdateProjectUseCase implements IUseCase {
    private final IProjectRepository projectRepository;
    private final Project project;

    /**
     * Creates an update project use case.
     *
     * @param projectRepository repository used to load and persist projects
     * @param project project carrying the updated state
     */
    public UpdateProjectUseCase(
        IProjectRepository projectRepository,
        Project project
    ) {
        this.projectRepository = projectRepository;
        this.project = project;
    }

    /**
     * Updates an existing project if it exists in the repository.
     */
    @Override
    public void execute() {
        Project existingProject = projectRepository.getProjectById(project.id);

        if (existingProject == null) {
            return;
        }

        projectRepository.updateProject(project);
    }
}
