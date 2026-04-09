package com.thorekt.tasktooine_api.adapter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thorekt.tasktooine_api.adapter.dto.ProjectRecord;
import com.thorekt.tasktooine_api.adapter.dto.TaskRecord;
import com.thorekt.tasktooine_api.core.entity.Project;
import com.thorekt.tasktooine_api.core.entity.Task;
import com.thorekt.tasktooine_api.core.entity.TaskStatus;
import com.thorekt.tasktooine_api.core.usecase.project.IProjectRepository;

/**
 * Unit tests for {@link ProjectController} using Mockito for dependency
 * injection.
 *
 * @author Thorekt
 */
@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {
    @Mock
    private IProjectRepository projectRepository;

    @InjectMocks
    private ProjectController projectController;

    @Test
    void createProjectShouldPersistProjectAndReturnPersistedRecord() {
        ProjectRecord input = new ProjectRecord("project-1", "Name", "Description", List.of());
        Project persistedProject = new Project("project-1", "Name", "Description");

        when(projectRepository.getProjectById("project-1")).thenReturn(persistedProject);

        ProjectRecord result = projectController.createProject(input);

        ArgumentCaptor<Project> projectCaptor = ArgumentCaptor.forClass(Project.class);
        verify(projectRepository).createProject(projectCaptor.capture());
        assertEquals("project-1", projectCaptor.getValue().id);
        assertEquals("project-1", result.id());
    }

    @Test
    void getProjectByIdShouldReturnNullWhenRepositoryReturnsNull() {
        when(projectRepository.getProjectById("missing")).thenReturn(null);

        ProjectRecord result = projectController.getProjectById("missing");

        assertNull(result);
    }

    @Test
    void getProjectByIdShouldReturnProjectRecordWhenProjectExists() {
        when(projectRepository.getProjectById("project-1"))
                .thenReturn(new Project("project-1", "Name", "Description"));

        ProjectRecord result = projectController.getProjectById("project-1");

        assertEquals("project-1", result.id());
        assertEquals("Name", result.name());
    }

    @Test
    void updateProjectShouldPersistUpdatedProjectAndReturnPersistedRecord() {
        ProjectRecord input = new ProjectRecord("ignored", "New name", "New description", List.of());
        Project persistedProject = new Project("project-1", "New name", "New description");

        when(projectRepository.getProjectById("project-1")).thenReturn(persistedProject);

        ProjectRecord result = projectController.updateProject("project-1", input);

        ArgumentCaptor<Project> projectCaptor = ArgumentCaptor.forClass(Project.class);
        verify(projectRepository).updateProject(projectCaptor.capture());
        assertEquals("project-1", projectCaptor.getValue().id);
        assertEquals("New name", projectCaptor.getValue().name);
        assertEquals("project-1", result.id());
    }

    @Test
    void updateProjectShouldReturnNullWhenRepositoryCannotLoadProjectAfterUpdate() {
        when(projectRepository.getProjectById("project-1")).thenReturn(null);

        ProjectRecord result = projectController.updateProject(
                "project-1",
                new ProjectRecord("ignored", "New name", "New description", List.of()));

        verify(projectRepository, never()).updateProject(any(Project.class));
        assertNull(result);
    }

    @Test
    void addTaskToProjectShouldPersistTaskAndReturnPersistedProject() {
        TaskRecord input = new TaskRecord("task-1", "Task", "Description", TaskStatus.BACKLOG);
        Project loadedProject = new Project(
                "project-1",
                "Name",
                "Description");
        Project persistedProject = new Project(
                "project-1",
                "Name",
                "Description",
                new ArrayList<>(List.of(new Task("task-1", "Task", "Description", TaskStatus.BACKLOG))));

        when(projectRepository.getProjectById("project-1")).thenReturn(loadedProject, persistedProject);

        ProjectRecord result = projectController.addTaskToProject("project-1", input);

        ArgumentCaptor<Project> projectCaptor = ArgumentCaptor.forClass(Project.class);
        verify(projectRepository).updateProject(projectCaptor.capture());
        assertEquals(1, projectCaptor.getValue().tasks.size());
        assertEquals("task-1", result.tasks().getFirst().id());
    }

    @Test
    void addTaskToProjectShouldReturnNullWhenProjectDoesNotExist() {
        when(projectRepository.getProjectById("missing")).thenReturn(null);

        ProjectRecord result = projectController.addTaskToProject(
                "missing",
                new TaskRecord("task-1", "Task", "Description", TaskStatus.BACKLOG));

        verify(projectRepository, never()).updateProject(any(Project.class));
        assertNull(result);
    }

    @Test
    void removeTaskFromProjectShouldPersistProjectAndReturnPersistedRecord() {
        Project mutableProject = new Project(
                "project-1",
                "Name",
                "Description",
                new ArrayList<>(List.of(new Task("task-1", "Task", "Description"))));

        when(projectRepository.getProjectById("project-1")).thenReturn(mutableProject);

        ProjectRecord result = projectController.removeTaskFromProject("project-1", "task-1");

        ArgumentCaptor<Project> projectCaptor = ArgumentCaptor.forClass(Project.class);
        verify(projectRepository).updateProject(projectCaptor.capture());
        assertEquals(0, projectCaptor.getValue().tasks.size());
        assertEquals(0, result.tasks().size());
    }

    @Test
    void removeTaskFromProjectShouldReturnProjectRecordWhenTaskIsMissing() {
        Project mutableProject = new Project(
                "project-1",
                "Name",
                "Description",
                new ArrayList<>(List.of(new Task("task-1", "Task", "Description"))));

        when(projectRepository.getProjectById("project-1")).thenReturn(mutableProject);

        ProjectRecord result = projectController.removeTaskFromProject("project-1", "missing-task");

        verify(projectRepository, never()).updateProject(any(Project.class));
        assertEquals(1, result.tasks().size());
    }

    @Test
    void removeTaskFromProjectShouldReturnNullWhenProjectDoesNotExist() {
        when(projectRepository.getProjectById("missing")).thenReturn(null);

        ProjectRecord result = projectController.removeTaskFromProject("missing", "task-1");

        verify(projectRepository, never()).updateProject(any(Project.class));
        assertNull(result);
    }

    @Test
    void deleteProjectShouldDelegateDeletionToRepository() {
        projectController.deleteProject("project-1");

        verify(projectRepository).deleteProject("project-1");
    }
}
