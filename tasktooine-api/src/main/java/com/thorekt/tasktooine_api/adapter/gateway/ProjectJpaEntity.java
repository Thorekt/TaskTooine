package com.thorekt.tasktooine_api.adapter.gateway;

import java.util.ArrayList;
import java.util.List;

import com.thorekt.tasktooine_api.core.entity.Project;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA persistence entity for projects.
 * This entity is dedicated to database storage and must stay separate from the
 * core domain entity.
 *
 * @author Thorekt
 */
@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectJpaEntity {
    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_id")
    private List<TaskJpaEntity> tasks = new ArrayList<>();

    /**
     * Creates a JPA project entity from a domain project.
     *
     * @param project domain project to convert
     * @return converted JPA entity
     */
    public static ProjectJpaEntity fromDomainEntity(Project project) {
        if (project == null) {
            return null;
        }

        return ProjectJpaEntity.builder()
            .id(project.id)
            .name(project.name)
            .description(project.description)
            .tasks(project.tasks == null
                ? new ArrayList<>()
                : project.tasks.stream().map(TaskJpaEntity::fromDomainEntity).toList())
            .build();
    }

    /**
     * Converts this JPA project entity to a domain project.
     *
     * @return converted domain project
     */
    public Project toDomainEntity() {
        return new Project(
            id,
            name,
            description,
            tasks == null
                ? new ArrayList<>()
                : tasks.stream().map(TaskJpaEntity::toDomainEntity).toList()
        );
    }
}
