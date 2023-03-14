package com.example.mytaskmanagersimbirsofr.controller;

import com.example.mytaskmanagersimbirsofr.entity.Task;
import com.example.mytaskmanagersimbirsofr.repository.TaskRepo;
import com.example.mytaskmanagersimbirsofr.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
@PreAuthorize("hasAuthority('MANAGER')")
public class TaskController {
    @Autowired
    private final TaskService taskService;
    @Autowired
    private TaskRepo taskRepo;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{id}")
    public String taskEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        return "taskEdit";
    }

    @PostMapping("{id}")
    public String taskSave(
            @RequestParam String title,
            @RequestParam String performer,
            @RequestParam String releaseVersion,
            @PathVariable("id") Long id) {
        Task task = taskService.getTaskById(id);
        task.setTitle(title);
        task.setPerformer(performer);
        task.setReleaseVersion(releaseVersion);
        taskService.saveTask(task);
        return "redirect:/dashboard";
    }
}
