package com.thorekt.tasktooine_api.adapter.gateway;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data repository for project persistence entities.
 *
 * @author Thorekt
 */
public interface ProjectJpaRepository extends JpaRepository<ProjectJpaEntity, String> {
}
