package com.thorekt.tasktooine_api.core.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a project in the task management system.
 * A project can have multiple tasks associated with it.
 * Fields:
 * - id: Unique identifier for the project.
 * - name: Name of the project.
 * - description: A brief description of the project.
 * - tasks: A list of tasks that belong to this project.
 * 
 * @author Thorekt
 */
public class Project {
    public String id;
    public String name;
    public String description;
    public List<Task> tasks;

    public Project(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tasks = new ArrayList<>();
    }

    public Project(String id, String name, String description, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tasks = tasks;
    }
}
