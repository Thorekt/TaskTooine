package com.thorekt.tasktooine_api.core.entity;

/**
 * Represents a task in the task management system.
 * A task belongs to a project and has a status that indicates its current
 * state.
 * Fields:
 * - id: Unique identifier for the task.
 * - title: Title of the task.
 * - description: A brief description of the task.
 * - status: The current status of the task (see TaskStatus).
 * 
 * @author Thorekt
 */
public class Task {
    public String id;
    public String title;
    public String description;
    public TaskStatus status;

    public Task(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = TaskStatus.BACKLOG;
    }

    public Task(String id, String title, String description, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
