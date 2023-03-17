package com.example.mytaskmanagersimbirsofr.controller;

import com.example.mytaskmanagersimbirsofr.entity.Project;
import com.example.mytaskmanagersimbirsofr.entity.Task;
import com.example.mytaskmanagersimbirsofr.service.ProjectService;
import com.example.mytaskmanagersimbirsofr.service.ReleaseService;
import com.example.mytaskmanagersimbirsofr.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class DashboardController {
    private final ProjectService projectService;
    private final TaskService taskService;
    private final ReleaseService releaseService;

    public DashboardController(ProjectService projectService, TaskService taskService, ReleaseService releaseService) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.releaseService = releaseService;

    }

    @GetMapping("/")
    @ApiOperation("Приветственный экран")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/dashboard")
    @ApiOperation("Список всех проектов")
    public String dashboard(Map<String, Object> model) {
        model.put("projectList", projectService.showProjects());
        return "dashboard";
    }

    @PostMapping("/dashboard")
    @ApiOperation("Добавить новый проект")
    public String addProject(@RequestParam String name,
                             Map<String, Object> model) {
        projectService.addProject(name);
        model.put("projectList", projectService.showProjects());
        return "dashboard";
    }

    @GetMapping("/dashboard/{id}")
    @ApiOperation("Показать список задач проекта")
    public String showTasks(@PathVariable("id") Project id, Map<String, Object> model) {
        model.put("tasks", taskService.getTaskList(id));
        return "project";
    }

    @PostMapping("/dashboard/{id}")
    @ApiOperation("Добавить новую задачу на проект и время ее релиза")
    public String addTask(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam String performer,
            @PathVariable("id") Project id,
            Map<String, Object> model
    ) {
        Task task = new Task(title, author, performer, id);
        taskService.saveTask(task);
        releaseService.addRelease("1.0", task);
        model.put("tasks", taskService.getTaskList(id));
        return "project";
    }
}
