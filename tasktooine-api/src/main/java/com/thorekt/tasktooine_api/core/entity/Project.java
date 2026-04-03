package com.thorekt.tasktooine_api.core.entity;

import java.util.ArrayList;
import java.util.List;

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
