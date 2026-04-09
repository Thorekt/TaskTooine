package com.thorekt.tasktooine_api.adapter.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.thorekt.tasktooine_api.core.entity.Task;
import com.thorekt.tasktooine_api.core.entity.TaskStatus;

class TaskJpaEntityTest {
    @Test
    void fromDomainEntityShouldReturnNullForNullInput() {
        assertNull(TaskJpaEntity.fromDomainEntity(null));
    }

    @Test
    void fromDomainEntityShouldMapTaskFields() {
        Task task = new Task("task-1", "Title", "Description", TaskStatus.BLOCKED);

        TaskJpaEntity entity = TaskJpaEntity.fromDomainEntity(task);

        assertEquals("task-1", entity.getId());
        assertEquals("Title", entity.getTitle());
        assertEquals("Description", entity.getDescription());
        assertEquals(TaskStatus.BLOCKED, entity.getStatus());
    }

    @Test
    void toDomainEntityShouldMapTaskFields() {
        TaskJpaEntity entity = TaskJpaEntity.builder()
                .id("task-1")
                .title("Title")
                .description("Description")
                .status(TaskStatus.DONE)
                .build();

        Task task = entity.toDomainEntity();

        assertEquals("task-1", task.id);
        assertEquals("Title", task.title);
        assertEquals("Description", task.description);
        assertEquals(TaskStatus.DONE, task.status);
    }
}
