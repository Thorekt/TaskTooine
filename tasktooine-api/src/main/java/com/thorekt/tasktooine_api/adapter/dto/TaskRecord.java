package com.thorekt.tasktooine_api.adapter.dto;

import com.thorekt.tasktooine_api.core.entity.Task;
import com.thorekt.tasktooine_api.core.entity.TaskStatus;

/**
 * Data Transfer Object (DTO) for Task entity.
 * This record is used to transfer task data between the API layer and the core
 * entity layer.
 * 
 * Refer to Task.java for the core entity definition and TaskStatus.java for the
 * possible status values.
 * 
 * @author Thorekt
 */
public record TaskRecord(
        String id,
        String title,
        String description,
        TaskStatus status) {

    /**
     * Converts a Task entity to a TaskRecord DTO.
     * 
     * @param task
     * @return
     */
    public static TaskRecord fromEntity(com.thorekt.tasktooine_api.core.entity.Task task) {
        return new TaskRecord(
                task.id,
                task.title,
                task.description,
                task.status);
    }

    /**
     * Converts a TaskRecord DTO to a Task entity.
     * 
     * @return
     */
    public Task toEntity() {
        return new Task(
                this.id,
                this.title,
                this.description,
                this.status);
    }
}
