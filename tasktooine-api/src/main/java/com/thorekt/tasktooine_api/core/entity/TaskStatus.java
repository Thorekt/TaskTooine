package com.thorekt.tasktooine_api.core.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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

    /**
     * Returns the serialized value of the task status.
     *
     * @return serialized task status value
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Creates a task status from an input value.
     * Accepts both enum names and serialized values.
     *
     * @param value raw status value
     * @return matching task status
     */
    @JsonCreator
    public static TaskStatus fromValue(String value) {
        if (value == null) {
            return null;
        }

        for (TaskStatus status : TaskStatus.values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }

        throw new IllegalArgumentException("Unknown task status: " + value);
    }
}
