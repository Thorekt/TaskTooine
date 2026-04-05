package com.thorekt.tasktooine_api.adapter.gateway;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data repository for task persistence entities.
 *
 * @author Thorekt
 */
public interface TaskJpaRepository extends JpaRepository<TaskJpaEntity, String> {
}
