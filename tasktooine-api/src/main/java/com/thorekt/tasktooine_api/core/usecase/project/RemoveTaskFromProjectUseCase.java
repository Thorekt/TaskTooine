package com.thorekt.tasktooine_api.core.usecase.project;

import com.thorekt.tasktooine_api.core.entity.Project;
import com.thorekt.tasktooine_api.core.usecase.IUseCase;

public class RemoveTaskFromProjectUseCase implements IUseCase {
    private final IProjectRepository projectRepository;
    private final String projectId;
    private final String taskId;

    public RemoveTaskFromProjectUseCase(IProjectRepository projectRepository, String projectId, String taskId) {
        this.projectRepository = projectRepository;
        this.projectId = projectId;
        this.taskId = taskId;
    }

    @Override
    public void execute() {
        Project project = projectRepository.getProjectById(projectId);

        if (project == null) {
            return;
        }

        boolean removed = project.tasks.removeIf(task -> task.id.equals(taskId));

        if (!removed) {
            return;
        }

        projectRepository.updateProject(project);
    }
}
