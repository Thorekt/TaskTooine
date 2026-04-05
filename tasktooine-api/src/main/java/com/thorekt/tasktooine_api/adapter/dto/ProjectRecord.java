package com.thorekt.tasktooine_api.adapter.dto;

import com.thorekt.tasktooine_api.core.entity.Project;

/**
 * Data Transfer Object (DTO) for Project entity.
 * This record is used to transfer project data between the API layer and the
 * core entity layer.
 * 
 * Refer to Project.java for the core entity definition and TaskRecord.java for
 * the associated tasks.
 */
public record ProjectRecord(
        String id,
        String name,
        String description,
        java.util.List<TaskRecord> tasks) {

    /**
     * Converts a Project entity to a ProjectRecord DTO.
     * 
     * @param project
     * @return
     */
    public static ProjectRecord fromEntity(Project project) {
        return new ProjectRecord(
                project.id,
                project.name,
                project.description,
                project.tasks.stream().map(TaskRecord::fromEntity).toList());
    }

    /**
     * Converts a ProjectRecord DTO to a Project entity.
     * 
     * @return
     */
    public Project toEntity() {
        return new Project(
                this.id,
                this.name,
                this.description,
                this.tasks.stream().map(TaskRecord::toEntity).toList());
    }
}