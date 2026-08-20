package ru.krastti.repository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.krastti.entity.Task;
import ru.krastti.enums.Status;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    default Task getTaskById(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Задания с таким ID не существует"));
    }

    List<Task> getTasksByProjectId(Long projectId);

    List<Task> getTasksByProjectIdAndStatus(Long projectId, Status status);
}
