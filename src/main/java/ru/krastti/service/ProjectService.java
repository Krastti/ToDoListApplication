package ru.krastti.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.krastti.entity.Project;
import ru.krastti.entity.User;
import ru.krastti.repository.ProjectRepository;
import ru.krastti.repository.UserRepository;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createProject(Long userId, String name, String description) {
        User user = userRepository.getUserById(userId);
        Project project = new Project(name, description, user);
        projectRepository.save(project);
    }

    @Transactional(readOnly = true)
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Project getProjectById(Long id) {
        return projectRepository.getProjectById(id);
    }

    @Transactional
    public void updateProject(Long id, String name, String description) {
        Project project = projectRepository.getProjectById(id);
        project.setName(name);
        project.setDescription(description);
    }

    @Transactional
    public void updateProjectName(Long id, String name) {
        Project project = projectRepository.getProjectById(id);
        project.setName(name);
    }

    @Transactional
    public void updateProjectDescription(Long id, String description) {
        Project project = projectRepository.getProjectById(id);
        project.setDescription(description);
    }

    @Transactional
    public void deleteProjectById(Long id) {
        projectRepository.deleteById(id);
    }
}
