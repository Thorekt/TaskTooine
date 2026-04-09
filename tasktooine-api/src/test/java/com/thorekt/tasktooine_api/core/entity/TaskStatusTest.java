package com.thorekt.tasktooine_api.core.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class TaskStatusTest {

    @Test
    void testGetValueOnAllValues() {
        assertEquals("backlog", TaskStatus.BACKLOG.getValue());
        assertEquals("in_progress", TaskStatus.IN_PROGRESS.getValue());
        assertEquals("done", TaskStatus.DONE.getValue());
        assertEquals("testing", TaskStatus.TESTING.getValue());
        assertEquals("blocked", TaskStatus.BLOCKED.getValue());
    }

    @Test
    void testFromValueOnAllValues() {
        assertEquals(TaskStatus.BACKLOG, TaskStatus.fromValue("backlog"));
        assertEquals(TaskStatus.IN_PROGRESS, TaskStatus.fromValue("in_progress"));
        assertEquals(TaskStatus.DONE, TaskStatus.fromValue("done"));
        assertEquals(TaskStatus.TESTING, TaskStatus.fromValue("testing"));
        assertEquals(TaskStatus.BLOCKED, TaskStatus.fromValue("blocked"));
    }

    @Test
    void testFromValueWithNull() {
        assertNull(TaskStatus.fromValue(null));
    }

    @Test
    void testFromValueWithInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> TaskStatus.fromValue("invalid_status"));
    }

    @Test
    void testFromValueWithCaseInsensitive() {
        assertEquals(TaskStatus.BACKLOG, TaskStatus.fromValue("BACKLOG"));
        assertEquals(TaskStatus.IN_PROGRESS, TaskStatus.fromValue("IN_PROGRESS"));
        assertEquals(TaskStatus.DONE, TaskStatus.fromValue("DONE"));
        assertEquals(TaskStatus.TESTING, TaskStatus.fromValue("TESTING"));
        assertEquals(TaskStatus.BLOCKED, TaskStatus.fromValue("BLOCKED"));
    }

}
