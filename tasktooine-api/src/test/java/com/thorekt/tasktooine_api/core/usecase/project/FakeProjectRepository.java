package com.thorekt.tasktooine_api.core.usecase.project;

import java.util.HashMap;
import java.util.Map;

import com.thorekt.tasktooine_api.core.entity.Project;

public class FakeProjectRepository implements IProjectRepository {
    public final Map<String, Project> projects = new HashMap<>();
    public Project createdProject;
    public Project updatedProject;
    public String deletedProjectId;

    @Override
    public void createProject(Project project) {
        createdProject = project;
        projects.put(project.id, project);
    }

    @Override
    public Project getProjectById(String id) {
        return projects.get(id);
    }

    @Override
    public void updateProject(Project project) {
        updatedProject = project;
        projects.put(project.id, project);
    }

    @Override
    public void deleteProject(String id) {
        deletedProjectId = id;
        projects.remove(id);
    }
}
