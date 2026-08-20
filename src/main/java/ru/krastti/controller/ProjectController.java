package ru.krastti.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.krastti.entity.Project;
import ru.krastti.service.ProjectService;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private static final Long TEMP_USER_ID = 1L; //TODO Заменить на реального пользователя

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("")
    public String listProjects(Model model) {
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "projects/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("project", new Project());
        return "projects/form";
    }

    @PostMapping("/new")
    public String createProject(@Valid @ModelAttribute("project") Project project,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("project", project);
            return "projects/form";
        }
        projectService.createProject(TEMP_USER_ID, project.getName(), project.getDescription());
        return "redirect:/projects";
    }

    @GetMapping("/{id}")
    public String viewProject(@PathVariable Long id) {
        //noinspection SpringMVCViewInspection
        return "redirect:/projects/" + id + "/tasks";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Project project = projectService.getProjectById(id);
        model.addAttribute("project", project);
        return "projects/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateProject(@PathVariable Long id,
                                @Valid @ModelAttribute("project") Project project,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("project", project);
            return "projects/edit";
        }
        projectService.updateProject(id, project.getName(), project.getDescription());
        return "redirect:/projects";
    }

    @PostMapping("/{id}/delete")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProjectById(id);
        return "redirect:/projects";
    }
}
