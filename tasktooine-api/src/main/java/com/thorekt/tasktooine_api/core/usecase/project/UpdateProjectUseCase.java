package com.thorekt.tasktooine_api.core.usecase.project;

import com.thorekt.tasktooine_api.core.entity.Project;
import com.thorekt.tasktooine_api.core.usecase.IUseCase;

public class UpdateProjectUseCase implements IUseCase {
    private final IProjectRepository projectRepository;
    private final String projectId;
    private final String name;
    private final String description;

    public UpdateProjectUseCase(
        IProjectRepository projectRepository,
        String projectId,
        String name,
        String description
    ) {
        this.projectRepository = projectRepository;
        this.projectId = projectId;
        this.name = name;
        this.description = description;
    }

    @Override
    public void execute() {
        Project project = projectRepository.getProjectById(projectId);

        if (project == null) {
            return;
        }

        if (name != null) {
            project.name = name;
        }

        if (description != null) {
            project.description = description;
        }

        projectRepository.updateProject(project);
    }
}
