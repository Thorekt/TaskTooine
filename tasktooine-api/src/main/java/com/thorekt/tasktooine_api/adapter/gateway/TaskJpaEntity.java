package com.thorekt.tasktooine_api.adapter.gateway;

import com.thorekt.tasktooine_api.core.entity.Task;
import com.thorekt.tasktooine_api.core.entity.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA persistence entity for tasks.
 * This entity is dedicated to database storage and must stay separate from the
 * core domain entity.
 *
 * @author Thorekt
 */
@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskJpaEntity {
    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    /**
     * Creates a JPA task entity from a domain task.
     *
     * @param task domain task to convert
     * @return converted JPA entity
     */
    public static TaskJpaEntity fromDomainEntity(Task task) {
        if (task == null) {
            return null;
        }

        return TaskJpaEntity.builder()
            .id(task.id)
            .title(task.title)
            .description(task.description)
            .status(task.status)
            .build();
    }

    /**
     * Converts this JPA task entity to a domain task.
     *
     * @return converted domain task
     */
    public Task toDomainEntity() {
        return new Task(id, title, description, status);
    }
}
