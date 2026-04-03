package com.thorekt.tasktooine_api.core.usecase.project;

import com.thorekt.tasktooine_api.core.entity.Project;
import com.thorekt.tasktooine_api.core.entity.Task;
import com.thorekt.tasktooine_api.core.usecase.IUseCase;

public class AddTaskToProjectUseCase implements IUseCase {
    private final IProjectRepository projectRepository;
    private final String projectId;
    private final Task task;

    public AddTaskToProjectUseCase(IProjectRepository projectRepository, String projectId, Task task) {
        this.projectRepository = projectRepository;
        this.projectId = projectId;
        this.task = task;
    }

    @Override
    public void execute() {
        Project project = projectRepository.getProjectById(projectId);

        if (project == null) {
            return;
        }

        project.tasks.add(task);

        projectRepository.updateProject(project);
    }
}
