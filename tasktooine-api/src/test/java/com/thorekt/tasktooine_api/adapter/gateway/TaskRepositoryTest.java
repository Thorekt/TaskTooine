package com.thorekt.tasktooine_api.adapter.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thorekt.tasktooine_api.core.entity.Task;
import com.thorekt.tasktooine_api.core.entity.TaskStatus;

@ExtendWith(MockitoExtension.class)
class TaskRepositoryTest {
    @Mock
    private TaskJpaRepository taskJpaRepository;

    @InjectMocks
    private TaskRepository taskRepository;

    @Test
    void createTaskShouldConvertAndPersistJpaEntity() {
        Task task = new Task("task-1", "Title", "Description", TaskStatus.TESTING);

        taskRepository.createTask(task);

        ArgumentCaptor<TaskJpaEntity> captor = ArgumentCaptor.forClass(TaskJpaEntity.class);
        verify(taskJpaRepository).save(captor.capture());
        assertEquals("task-1", captor.getValue().getId());
        assertEquals("Title", captor.getValue().getTitle());
        assertEquals(TaskStatus.TESTING, captor.getValue().getStatus());
    }

    @Test
    void getTaskByIdShouldReturnConvertedDomainEntityWhenPresent() {
        TaskJpaEntity entity = TaskJpaEntity.builder()
                .id("task-1")
                .title("Title")
                .description("Description")
                .status(TaskStatus.BLOCKED)
                .build();
        when(taskJpaRepository.findById("task-1")).thenReturn(Optional.of(entity));

        Task result = taskRepository.getTaskById("task-1");

        assertNotNull(result);
        assertEquals("task-1", result.id);
        assertEquals("Title", result.title);
        assertEquals(TaskStatus.BLOCKED, result.status);
    }

    @Test
    void getTaskByIdShouldReturnNullWhenMissing() {
        when(taskJpaRepository.findById("missing")).thenReturn(Optional.empty());

        Task result = taskRepository.getTaskById("missing");

        assertNull(result);
    }

    @Test
    void updateTaskShouldConvertAndPersistJpaEntity() {
        Task task = new Task("task-1", "Updated title", "Description", TaskStatus.IN_PROGRESS);

        taskRepository.updateTask(task);

        ArgumentCaptor<TaskJpaEntity> captor = ArgumentCaptor.forClass(TaskJpaEntity.class);
        verify(taskJpaRepository).save(captor.capture());
        assertEquals("task-1", captor.getValue().getId());
        assertEquals("Updated title", captor.getValue().getTitle());
        assertEquals(TaskStatus.IN_PROGRESS, captor.getValue().getStatus());
    }

    @Test
    void deleteTaskShouldDelegateToJpaRepository() {
        taskRepository.deleteTask("task-1");

        verify(taskJpaRepository).deleteById("task-1");
    }
}
