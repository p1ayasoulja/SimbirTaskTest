package com.example.mytaskmanagersimbirsofr.controller;

import com.example.mytaskmanagersimbirsofr.entity.Project;
import com.example.mytaskmanagersimbirsofr.service.ProjectService;
import com.example.mytaskmanagersimbirsofr.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class DashboardController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/dashboard")
    public String dashboard(Map<String, Object> model) {
        model.put("projectList", projectService.showProjects());
        return "projects";
    }

    @PostMapping("/dashboard")
    public String addProject(@RequestParam String name,
                             Map<String, Object> model) {
        projectService.addProject(name);
        model.put("projectList", projectService.showProjects());
        return "projects";
    }

    @GetMapping("/dashboard/{id}")
    public String showTasks(@PathVariable("id") Project id, Map<String, Object> model) {
        model.put("tasks", taskService.showTaskList(id));
        return "tasks";
    }

    @PostMapping("/dashboard/{id}")
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
