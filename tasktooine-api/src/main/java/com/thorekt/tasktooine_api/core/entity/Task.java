package com.thorekt.tasktooine_api.core.entity;

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
}
