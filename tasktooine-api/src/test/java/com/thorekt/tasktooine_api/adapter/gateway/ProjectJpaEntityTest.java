package com.thorekt.tasktooine_api.adapter.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.thorekt.tasktooine_api.core.entity.Project;
import com.thorekt.tasktooine_api.core.entity.Task;
import com.thorekt.tasktooine_api.core.entity.TaskStatus;

class ProjectJpaEntityTest {
    @Test
    void fromDomainEntityShouldReturnNullForNullInput() {
        assertNull(ProjectJpaEntity.fromDomainEntity(null));
    }

    @Test
    void fromDomainEntityShouldMapProjectAndTasks() {
        Project project = new Project(
                "project-1",
                "Name",
                "Description",
                new ArrayList<>(List.of(new Task("task-1", "Task", "Task description", TaskStatus.TESTING))));

        ProjectJpaEntity entity = ProjectJpaEntity.fromDomainEntity(project);

        assertNotNull(entity);
        assertEquals("project-1", entity.getId());
        assertEquals("Name", entity.getName());
        assertEquals("Description", entity.getDescription());
        assertEquals(1, entity.getTasks().size());
        assertEquals("task-1", entity.getTasks().getFirst().getId());
        assertEquals(TaskStatus.TESTING, entity.getTasks().getFirst().getStatus());
    }

    @Test
    void fromDomainEntityShouldCreateEmptyTasksWhenDomainTasksAreNull() {
        Project project = new Project("project-1", "Name", "Description", null);

        ProjectJpaEntity entity = ProjectJpaEntity.fromDomainEntity(project);

        assertNotNull(entity);
        assertNotNull(entity.getTasks());
        assertTrue(entity.getTasks().isEmpty());
    }

    @Test
    void toDomainEntityShouldMapProjectAndTasks() {
        ProjectJpaEntity entity = ProjectJpaEntity.builder()
                .id("project-1")
                .name("Name")
                .description("Description")
                .tasks(new ArrayList<>(List.of(TaskJpaEntity.builder()
                        .id("task-1")
                        .title("Task")
                        .description("Task description")
                        .status(TaskStatus.DONE)
                        .build())))
                .build();

        Project project = entity.toDomainEntity();

        assertEquals("project-1", project.id);
        assertEquals("Name", project.name);
        assertEquals("Description", project.description);
        assertEquals(1, project.tasks.size());
        assertEquals("task-1", project.tasks.getFirst().id);
        assertEquals(TaskStatus.DONE, project.tasks.getFirst().status);
    }

    @Test
    void toDomainEntityShouldCreateEmptyTasksWhenJpaTasksAreNull() {
        ProjectJpaEntity entity = ProjectJpaEntity.builder()
                .id("project-1")
                .name("Name")
                .description("Description")
                .tasks(null)
                .build();

        Project project = entity.toDomainEntity();

        assertNotNull(project.tasks);
        assertTrue(project.tasks.isEmpty());
    }
}
