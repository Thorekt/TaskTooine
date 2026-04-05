package com.thorekt.tasktooine_api.adapter.rest;

import java.net.URI;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thorekt.tasktooine_api.adapter.controller.TaskController;
import com.thorekt.tasktooine_api.adapter.dto.TaskRecord;
import com.thorekt.tasktooine_api.core.entity.TaskStatus;
import com.thorekt.tasktooine_api.core.usecase.task.ITaskRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST endpoints dedicated to task resources.
 * This class exposes the HTTP contract and delegates application logic to the
 * task controller.
 *
 * @author Thorekt
 */
@Validated
@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tasks", description = "Task management endpoints")
public class TaskEndpoints {
    private final TaskController taskController;

    /**
     * Creates the task REST endpoints with the repository required by the task
     * controller.
     *
     * @param taskRepository task repository implementation injected by Spring
     */
    public TaskEndpoints(ITaskRepository taskRepository) {
        this.taskController = new TaskController(taskRepository);
    }

    /**
     * Creates a task and returns the persisted representation.
     *
     * @param taskRecord task payload received from the client
     * @return created task response
     */
    @PostMapping
    @Operation(summary = "Create a task")
    public ResponseEntity<TaskRecord> createTask(@RequestBody TaskRecord taskRecord) {
        TaskRecord createdTask = taskController.createTask(taskRecord);
        return ResponseEntity
            .created(URI.create("/api/tasks/" + createdTask.id()))
            .body(createdTask);
    }

    /**
     * Retrieves a task by its identifier.
     *
     * @param taskId identifier of the task to retrieve
     * @return task response, or 404 if not found
     */
    @GetMapping("/{taskId}")
    @Operation(summary = "Get a task by id")
    public ResponseEntity<TaskRecord> getTaskById(@PathVariable String taskId) {
        TaskRecord taskRecord = taskController.getTaskById(taskId);

        if (taskRecord == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(taskRecord);
    }

    /**
     * Updates an existing task.
     *
     * @param taskId identifier of the task to update
     * @param taskRecord task payload received from the client
     * @return updated task response, or 404 if not found
     */
    @PutMapping("/{taskId}")
    @Operation(summary = "Update a task")
    public ResponseEntity<TaskRecord> updateTask(
            @PathVariable String taskId,
            @RequestBody TaskRecord taskRecord) {
        TaskRecord updatedTask = taskController.updateTask(taskId, taskRecord);

        if (updatedTask == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedTask);
    }

    /**
     * Updates only the status of an existing task.
     *
     * @param taskId identifier of the task to update
     * @param status new status of the task
     * @return updated task response, or 404 if not found
     */
    @PatchMapping("/{taskId}/status")
    @Operation(summary = "Update task status")
    public ResponseEntity<TaskRecord> updateTaskStatus(
            @PathVariable String taskId,
            @ParameterObject @RequestParam TaskStatus status) {
        TaskRecord updatedTask = taskController.updateTaskStatus(taskId, status);

        if (updatedTask == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedTask);
    }

    /**
     * Deletes a task by its identifier.
     *
     * @param taskId identifier of the task to delete
     * @return empty response with status 204
     */
    @DeleteMapping("/{taskId}")
    @Operation(summary = "Delete a task")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId) {
        taskController.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
