package ru.krastti.repository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.krastti.entity.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    default Project getProjectById(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Проекта с таким ID не существует"));
    }
}
