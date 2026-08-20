package ru.krastti.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import ru.krastti.entity.Project;
import ru.krastti.entity.Task;
import ru.krastti.enums.Status;
import ru.krastti.service.ProjectService;
import ru.krastti.service.TaskService;

import java.util.List;

@Controller
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {
    private final TaskService taskService;
    private final ProjectService projectService;

    @Autowired
    public TaskController(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
    }

    @GetMapping
    public String listTasks(@PathVariable Long projectId,
                            @RequestParam(required = false) Status status,
                            Model model) {
        List<Task> tasks;
        if (status != null) {
            tasks = taskService.getTasksByProjectAndStatus(projectId, status);
        } else {
            tasks = taskService.getTasksByProject(projectId);
        }
        Project project = projectService.getProjectById(projectId);
        model.addAttribute("tasks", tasks);
        model.addAttribute("projectId", projectId);
        model.addAttribute("projectName", project.getName());
        return "tasks/list";
    }

    @GetMapping("/new")
    public String showCreateForm(@PathVariable Long projectId, Model model) {
        model.addAttribute("projectId", projectId);
        model.addAttribute("task", new Task());
        return "tasks/form";
    }

    @PostMapping("/new")
    public String createTask(@PathVariable Long projectId,
                             @Valid @ModelAttribute("task") Task task,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("projectId", projectId);
            return "tasks/form";
        }
        taskService.createTask(projectId, task.getTitle(), task.getDescription(), task.getStatus(), task.getDeadline());
        //noinspection SpringMVCViewInspection
        return "redirect:/projects/" + projectId + "/tasks";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long projectId,
                               @PathVariable Long id,
                               Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        model.addAttribute("projectId", projectId);
        return "tasks/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateTask(@PathVariable Long projectId,
                             @Valid @ModelAttribute("task") Task task,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("projectId", projectId);
            return "tasks/edit";
        }
        taskService.updateTask(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getDeadline());
        //noinspection SpringMVCViewInspection
        return "redirect:/projects/" + projectId + "/tasks";
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long projectId,
                             @PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/projects/" + projectId + "/tasks";
    }
}
