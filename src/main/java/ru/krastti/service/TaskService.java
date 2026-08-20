package ru.krastti.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.krastti.entity.Project;
import ru.krastti.entity.Task;
import ru.krastti.enums.Status;
import ru.krastti.repository.ProjectRepository;
import ru.krastti.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional
    public void createTask(Long projectId, String title, String description, Status status, LocalDate deadline) {
        Project project = projectRepository.getProjectById(projectId);
        Task task = new Task(title, description, status, deadline, project);
        taskRepository.save(task);
    }

    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Task> getTasksByProject(Long projectId) {
        return taskRepository.getTasksByProjectId(projectId);
    }

    @Transactional(readOnly = true)
    public List<Task> getTasksByProjectAndStatus(Long projectId, Status status) {
        return taskRepository.getTasksByProjectIdAndStatus(projectId, status);
    }

    @Transactional(readOnly = true)
    public Task getTaskById(Long id) {
        return taskRepository.getTaskById(id);
    }

    @Transactional
    public void updateTask(Long id, String title, String description, Status status, LocalDate deadline) {
        Task task = taskRepository.getTaskById(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setDeadline(deadline);
    }

    @Transactional
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }
}
