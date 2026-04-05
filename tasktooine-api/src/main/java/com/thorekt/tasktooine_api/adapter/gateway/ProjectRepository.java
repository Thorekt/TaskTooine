package com.thorekt.tasktooine_api.adapter.gateway;

import com.thorekt.tasktooine_api.core.entity.Project;
import com.thorekt.tasktooine_api.core.usecase.project.IProjectRepository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

/**
 * Spring-backed gateway implementing the project repository contract.
 *
 * @author Thorekt
 */
@Repository
@RequiredArgsConstructor
public class ProjectRepository implements IProjectRepository {
    private final ProjectJpaRepository projectJpaRepository;

    /**
     * Persists a new project.
     *
     * @param project project to create
     */
    @Override
    public void createProject(Project project) {
        projectJpaRepository.save(ProjectJpaEntity.fromDomainEntity(project));
    }

    /**
     * Retrieves a project by its identifier.
     *
     * @param id identifier of the project to retrieve
     * @return the domain project, or {@code null} if not found
     */
    @Override
    public Project getProjectById(String id) {
        return projectJpaRepository.findById(id)
            .map(ProjectJpaEntity::toDomainEntity)
            .orElse(null);
    }

    /**
     * Updates an existing project.
     *
     * @param project project to update
     */
    @Override
    public void updateProject(Project project) {
        projectJpaRepository.save(ProjectJpaEntity.fromDomainEntity(project));
    }

    /**
     * Deletes a project by its identifier.
     *
     * @param id identifier of the project to delete
     */
    @Override
    public void deleteProject(String id) {
        projectJpaRepository.deleteById(id);
    }
}
