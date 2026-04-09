package com.thorekt.tasktooine_api.adapter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thorekt.tasktooine_api.adapter.dto.TaskRecord;
import com.thorekt.tasktooine_api.core.entity.Task;
import com.thorekt.tasktooine_api.core.entity.TaskStatus;
import com.thorekt.tasktooine_api.core.usecase.task.ITaskRepository;

/**
 * Unit tests for {@link TaskController} using Mockito for dependency injection.
 *
 * @author Thorekt
 */
@ExtendWith(MockitoExtension.class)
class TaskControllerTest {
    @Mock
    private ITaskRepository taskRepository;

    @InjectMocks
    private TaskController taskController;

    @Test
    void createTaskShouldPersistTaskAndReturnPersistedRecord() {
        TaskRecord input = new TaskRecord("task-1", "Title", "Description", TaskStatus.BACKLOG);
        Task persistedTask = new Task("task-1", "Title", "Description", TaskStatus.BACKLOG);

        when(taskRepository.getTaskById("task-1")).thenReturn(persistedTask);

        TaskRecord result = taskController.createTask(input);

        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository).createTask(taskCaptor.capture());
        assertEquals("task-1", taskCaptor.getValue().id);
        assertEquals(TaskStatus.BACKLOG, taskCaptor.getValue().status);
        assertEquals("task-1", result.id());
    }

    @Test
    void getTaskByIdShouldReturnNullWhenRepositoryReturnsNull() {
        when(taskRepository.getTaskById("missing")).thenReturn(null);

        TaskRecord result = taskController.getTaskById("missing");

        assertNull(result);
    }

    @Test
    void getTaskByIdShouldReturnTaskRecordWhenTaskExists() {
        when(taskRepository.getTaskById("task-1"))
                .thenReturn(new Task("task-1", "Title", "Description", TaskStatus.DONE));

        TaskRecord result = taskController.getTaskById("task-1");

        assertEquals("task-1", result.id());
        assertEquals(TaskStatus.DONE, result.status());
    }

    @Test
    void updateTaskShouldPersistUpdatedTaskAndReturnPersistedRecord() {
        TaskRecord input = new TaskRecord("ignored", "New title", "New description", TaskStatus.TESTING);
        Task persistedTask = new Task("task-1", "New title", "New description", TaskStatus.TESTING);

        when(taskRepository.getTaskById("task-1")).thenReturn(persistedTask);

        TaskRecord result = taskController.updateTask("task-1", input);

        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository).updateTask(taskCaptor.capture());
        assertEquals("task-1", taskCaptor.getValue().id);
        assertEquals("New title", taskCaptor.getValue().title);
        assertEquals(TaskStatus.TESTING, taskCaptor.getValue().status);
        assertEquals("task-1", result.id());
    }

    @Test
    void updateTaskShouldReturnNullWhenRepositoryCannotLoadTaskAfterUpdate() {
        when(taskRepository.getTaskById("task-1")).thenReturn(null);

        TaskRecord result = taskController.updateTask(
                "task-1",
                new TaskRecord("ignored", "New title", "New description", TaskStatus.DONE));

        verify(taskRepository, never()).updateTask(org.mockito.ArgumentMatchers.any(Task.class));
        assertNull(result);
    }

    @Test
    void updateTaskStatusShouldPersistStatusAndReturnPersistedRecord() {
        Task persistedTask = new Task("task-1", "Title", "Description", TaskStatus.BLOCKED);
        when(taskRepository.getTaskById("task-1")).thenReturn(persistedTask);

        TaskRecord result = taskController.updateTaskStatus("task-1", TaskStatus.BLOCKED);

        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository).updateTask(taskCaptor.capture());
        assertEquals(TaskStatus.BLOCKED, taskCaptor.getValue().status);
        assertEquals(TaskStatus.BLOCKED, result.status());
    }

    @Test
    void updateTaskStatusShouldReturnNullWhenTaskDoesNotExist() {
        when(taskRepository.getTaskById("missing")).thenReturn(null);

        TaskRecord result = taskController.updateTaskStatus("missing", TaskStatus.DONE);

        verify(taskRepository, never()).updateTask(org.mockito.ArgumentMatchers.any(Task.class));
        assertNull(result);
    }

    @Test
    void deleteTaskShouldDelegateDeletionToRepository() {
        taskController.deleteTask("task-1");

        verify(taskRepository).deleteTask("task-1");
    }
}
