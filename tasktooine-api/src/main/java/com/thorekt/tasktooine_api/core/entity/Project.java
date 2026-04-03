package com.thorekt.tasktooine_api.core.entity;

public class Project {
    public String id;
    public String name;
    public String description;
    public Task[] tasks;

    public Project(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tasks = new Task[0];
    }

    public Project(String id, String name, String description, Task[] tasks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tasks = tasks;
    }
}
