package com.thorekt.tasktooine_api.adapter.rest;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thorekt.tasktooine_api.adapter.controller.ProjectController;
import com.thorekt.tasktooine_api.adapter.dto.ProjectRecord;
import com.thorekt.tasktooine_api.adapter.dto.TaskRecord;
import com.thorekt.tasktooine_api.core.usecase.project.IProjectRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST endpoints dedicated to project resources.
 * This class exposes the HTTP contract and delegates application logic to the
 * project controller.
 *
 * @author Thorekt
 */
@Validated
@RestController
@RequestMapping("/api/projects")
@Tag(name = "Projects", description = "Project management endpoints")
public class ProjectEndpoints {
    private final ProjectController projectController;

    /**
     * Creates the project REST endpoints with the repository required by the
     * project controller.
     *
     * @param projectRepository project repository implementation injected by
     *                          Spring
     */
    public ProjectEndpoints(IProjectRepository projectRepository) {
        this.projectController = new ProjectController(projectRepository);
    }

    /**
     * Creates a project and returns the persisted representation.
     *
     * @param projectRecord project payload received from the client
     * @return created project response
     */
    @PostMapping
    @Operation(summary = "Create a project")
    public ResponseEntity<ProjectRecord> createProject(@RequestBody ProjectRecord projectRecord) {
        ProjectRecord createdProject = projectController.createProject(projectRecord);
        return ResponseEntity
                .created(URI.create("/api/projects/" + createdProject.id()))
                .body(createdProject);
    }

    /**
     * Retrieves a project by its identifier.
     *
     * @param projectId identifier of the project to retrieve
     * @return project response, or 404 if not found
     */
    @GetMapping("/{projectId}")
    @Operation(summary = "Get a project by id")
    public ResponseEntity<ProjectRecord> getProjectById(@PathVariable String projectId) {
        ProjectRecord projectRecord = projectController.getProjectById(projectId);

        if (projectRecord == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(projectRecord);
    }

    /**
     * Updates an existing project.
     *
     * @param projectId     identifier of the project to update
     * @param projectRecord project payload received from the client
     * @return updated project response, or 404 if not found
     */
    @PutMapping("/{projectId}")
    @Operation(summary = "Update a project")
    public ResponseEntity<ProjectRecord> updateProject(
            @PathVariable String projectId,
            @RequestBody ProjectRecord projectRecord) {
        ProjectRecord updatedProject = projectController.updateProject(projectId, projectRecord);

        if (updatedProject == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedProject);
    }

    /**
     * Adds a task to a project.
     *
     * @param projectId  identifier of the project to update
     * @param taskRecord task payload received from the client
     * @return updated project response, or 404 if not found
     */
    @PostMapping("/{projectId}/tasks")
    @Operation(summary = "Add a task to a project")
    public ResponseEntity<ProjectRecord> addTaskToProject(
            @PathVariable String projectId,
            @RequestBody TaskRecord taskRecord) {
        ProjectRecord updatedProject = projectController.addTaskToProject(projectId, taskRecord);

        if (updatedProject == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedProject);
    }

    /**
     * Removes a task from a project.
     *
     * @param projectId identifier of the project to update
     * @param taskId    identifier of the task to remove
     * @return updated project response, or 404 if not found
     */
    @DeleteMapping("/{projectId}/tasks/{taskId}")
    @Operation(summary = "Remove a task from a project")
    public ResponseEntity<ProjectRecord> removeTaskFromProject(
            @PathVariable String projectId,
            @PathVariable String taskId) {
        ProjectRecord updatedProject = projectController.removeTaskFromProject(projectId, taskId);

        if (updatedProject == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedProject);
    }

    /**
     * Deletes a project by its identifier.
     *
     * @param projectId identifier of the project to delete
     * @return empty response with status 204
     */
    @DeleteMapping("/{projectId}")
    @Operation(summary = "Delete a project")
    public ResponseEntity<Void> deleteProject(@PathVariable String projectId) {
        projectController.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }
}
