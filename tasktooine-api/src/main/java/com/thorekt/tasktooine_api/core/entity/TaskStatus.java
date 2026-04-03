package com.thorekt.tasktooine_api.core.entity;

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
