package com.example.mytaskmanagersimbirsofr.controller;

import com.example.mytaskmanagersimbirsofr.entity.Project;
import com.example.mytaskmanagersimbirsofr.service.ProjectService;
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

    public DashboardController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
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
        return "projects";
    }

    @PostMapping("/dashboard")
    @ApiOperation("Добавить новый проект")
    public String addProject(@RequestParam String name,
                             Map<String, Object> model) {
        projectService.addProject(name);
        model.put("projectList", projectService.showProjects());
        return "projects";
    }

    @GetMapping("/dashboard/{id}")
    @ApiOperation("Показать список задач проекта")
    public String showTasks(@PathVariable("id") Project id, Map<String, Object> model) {
        model.put("tasks", taskService.showTaskList(id));
        return "tasks";
    }

    @PostMapping("/dashboard/{id}")
    @ApiOperation("Добавить новую задачу на проект")
    public String addTask(@RequestParam String title,
                          @RequestParam String author,
                          @RequestParam String performer,
                          @PathVariable("id") Project id,
                          Map<String, Object> model) {
        taskService.addTask(title, author, performer, id);
        model.put("tasks", taskService.showTaskList(id));
        return "tasks";
    }
}
