package com.thorekt.tasktooine_api.core.usecase.project;

import com.thorekt.tasktooine_api.core.entity.Project;

public interface IProjectRepository {
    public void createProject(Project project);

    public Project getProjectById(String id);

    public void updateProject(Project project);

    public void deleteProject(String id);
}
