package com.thorekt.tasktooine_api.adapter.gateway;

import com.thorekt.tasktooine_api.core.entity.Task;
import com.thorekt.tasktooine_api.core.usecase.task.ITaskRepository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

/**
 * Spring-backed gateway implementing the task repository contract.
 *
 * @author Thorekt
 */
@Repository
@RequiredArgsConstructor
public class TaskRepository implements ITaskRepository {
    private final TaskJpaRepository taskJpaRepository;

    /**
     * Persists a new task.
     *
     * @param task task to create
     */
    @Override
    public void createTask(Task task) {
        taskJpaRepository.save(TaskJpaEntity.fromDomainEntity(task));
    }

    /**
     * Retrieves a task by its identifier.
     *
     * @param id identifier of the task to retrieve
     * @return the domain task, or {@code null} if not found
     */
    @Override
    public Task getTaskById(String id) {
        return taskJpaRepository.findById(id)
            .map(TaskJpaEntity::toDomainEntity)
            .orElse(null);
    }

    /**
     * Updates an existing task.
     *
     * @param task task to update
     */
    @Override
    public void updateTask(Task task) {
        taskJpaRepository.save(TaskJpaEntity.fromDomainEntity(task));
    }

    /**
     * Deletes a task by its identifier.
     *
     * @param id identifier of the task to delete
     */
    @Override
    public void deleteTask(String id) {
        taskJpaRepository.deleteById(id);
    }
}
