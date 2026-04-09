package com.thorekt.tasktooine_api.adapter.gateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thorekt.tasktooine_api.core.entity.Project;
import com.thorekt.tasktooine_api.core.entity.Task;
import com.thorekt.tasktooine_api.core.entity.TaskStatus;

@ExtendWith(MockitoExtension.class)
class ProjectRepositoryTest {
    @Mock
    private ProjectJpaRepository projectJpaRepository;

    @InjectMocks
    private ProjectRepository projectRepository;

    @Test
    void createProjectShouldConvertAndPersistJpaEntity() {
        Project project = new Project(
                "project-1",
                "Name",
                "Description",
                new ArrayList<>(List.of(new Task("task-1", "Task", "Description", TaskStatus.BACKLOG))));

        projectRepository.createProject(project);

        ArgumentCaptor<ProjectJpaEntity> captor = ArgumentCaptor.forClass(ProjectJpaEntity.class);
        verify(projectJpaRepository).save(captor.capture());
        assertEquals("project-1", captor.getValue().getId());
        assertEquals("Name", captor.getValue().getName());
        assertEquals(1, captor.getValue().getTasks().size());
        assertEquals("task-1", captor.getValue().getTasks().getFirst().getId());
    }

    @Test
    void getProjectByIdShouldReturnConvertedDomainEntityWhenPresent() {
        ProjectJpaEntity entity = ProjectJpaEntity.builder()
                .id("project-1")
                .name("Name")
                .description("Description")
                .tasks(new ArrayList<>(List.of(TaskJpaEntity.builder()
                        .id("task-1")
                        .title("Task")
                        .description("Description")
                        .status(TaskStatus.DONE)
                        .build())))
                .build();
        when(projectJpaRepository.findById("project-1")).thenReturn(Optional.of(entity));

        Project result = projectRepository.getProjectById("project-1");

        assertNotNull(result);
        assertEquals("project-1", result.id);
        assertEquals("Name", result.name);
        assertEquals(1, result.tasks.size());
        assertEquals(TaskStatus.DONE, result.tasks.getFirst().status);
    }

    @Test
    void getProjectByIdShouldReturnNullWhenMissing() {
        when(projectJpaRepository.findById("missing")).thenReturn(Optional.empty());

        Project result = projectRepository.getProjectById("missing");

        assertNull(result);
    }

    @Test
    void updateProjectShouldConvertAndPersistJpaEntity() {
        Project project = new Project("project-1", "Updated", "Description");

        projectRepository.updateProject(project);

        ArgumentCaptor<ProjectJpaEntity> captor = ArgumentCaptor.forClass(ProjectJpaEntity.class);
        verify(projectJpaRepository).save(captor.capture());
        assertEquals("project-1", captor.getValue().getId());
        assertEquals("Updated", captor.getValue().getName());
    }

    @Test
    void deleteProjectShouldDelegateToJpaRepository() {
        projectRepository.deleteProject("project-1");

        verify(projectJpaRepository).deleteById("project-1");
    }
}
