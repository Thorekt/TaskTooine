package com.thorekt.tasktooine_api.core.entity;

/**
 * Represents the status of a task in the task management system.
 * The status indicates the current state of a task and can be one of the
 * following:
 * - BACKLOG: The task is in the backlog and has not been started yet.
 * - IN_PROGRESS: The task is currently being worked on.
 * - DONE: The task has been completed.
 * - TESTING: The task is currently being tested.
 * - BLOCKED: The task is blocked and cannot be worked on until the issue
 * causing the block is resolved.
 * 
 * @author Thorekt
 */
public enum TaskStatus {
    BACKLOG("backlog"),
    IN_PROGRESS("in_progress"),
    DONE("done"),
    TESTING("testing"),
    BLOCKED("blocked");

    private final String value;

    TaskStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
