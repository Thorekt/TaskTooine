package com.thorekt.tasktooine_api.adapter.controller;

import com.thorekt.tasktooine_api.adapter.dto.ProjectRecord;
import com.thorekt.tasktooine_api.adapter.dto.TaskRecord;
import com.thorekt.tasktooine_api.core.entity.Project;
import com.thorekt.tasktooine_api.core.usecase.project.AddTaskToProjectUseCase;
import com.thorekt.tasktooine_api.core.usecase.project.CreateProjectUseCase;
import com.thorekt.tasktooine_api.core.usecase.project.DeleteProjectUseCase;
import com.thorekt.tasktooine_api.core.usecase.project.IProjectRepository;
import com.thorekt.tasktooine_api.core.usecase.project.RemoveTaskFromProjectUseCase;
import com.thorekt.tasktooine_api.core.usecase.project.UpdateProjectUseCase;

/**
 * Application controller dedicated to project operations.
 * This controller orchestrates project-related use cases and exposes DTO-based
 * methods for the outer layers of the application.
 */
public class ProjectController {
    private final IProjectRepository projectRepository;

    /**
     * Creates a project controller with the repository required by the use cases.
     *
     * @param projectRepository repository used to load and persist projects
     */
    public ProjectController(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * Creates a new project from the provided DTO and returns the persisted
     * project.
     *
     * @param projectRecord project data to create
     * @return the created project, or {@code null} if it cannot be retrieved
     */
    public ProjectRecord createProject(ProjectRecord projectRecord) {
        new CreateProjectUseCase(projectRepository, projectRecord.toEntity()).execute();
        return getProjectById(projectRecord.id());
    }

    /**
     * Retrieves a project by its identifier.
     *
     * @param projectId identifier of the project to retrieve
     * @return the matching project DTO, or {@code null} if no project is found
     */
    public ProjectRecord getProjectById(String projectId) {
        Project project = projectRepository.getProjectById(projectId);

        if (project == null) {
            return null;
        }

        return ProjectRecord.fromEntity(project);
    }

    /**
     * Updates the name and description of an existing project.
     *
     * @param projectId identifier of the project to update
     * @param projectRecord DTO carrying the updated values
     * @return the updated project DTO, or {@code null} if no project is found
     */
    public ProjectRecord updateProject(String projectId, ProjectRecord projectRecord) {
        Project project = projectRecord.toEntity();
        project.id = projectId;

        new UpdateProjectUseCase(projectRepository, project).execute();

        return getProjectById(projectId);
    }

    /**
     * Adds a task to the target project.
     *
     * @param projectId identifier of the project to update
     * @param taskRecord task to attach to the project
     * @return the updated project DTO, or {@code null} if no project is found
     */
    public ProjectRecord addTaskToProject(String projectId, TaskRecord taskRecord) {
        new AddTaskToProjectUseCase(projectRepository, projectId, taskRecord.toEntity()).execute();
        return getProjectById(projectId);
    }

    /**
     * Removes a task from the target project.
     *
     * @param projectId identifier of the project to update
     * @param taskId identifier of the task to remove
     * @return the updated project DTO, or {@code null} if no project is found
     */
    public ProjectRecord removeTaskFromProject(String projectId, String taskId) {
        new RemoveTaskFromProjectUseCase(projectRepository, projectId, taskId).execute();
        return getProjectById(projectId);
    }

    /**
     * Deletes a project by its identifier.
     *
     * @param projectId identifier of the project to delete
     */
    public void deleteProject(String projectId) {
        new DeleteProjectUseCase(projectRepository, projectId).execute();
    }
}
