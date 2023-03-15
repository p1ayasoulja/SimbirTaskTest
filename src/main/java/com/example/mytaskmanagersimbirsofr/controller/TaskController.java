package com.example.mytaskmanagersimbirsofr.controller;

import com.example.mytaskmanagersimbirsofr.entity.Task;
import com.example.mytaskmanagersimbirsofr.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{id}")
    @ApiOperation("Метод редактирования задачи")
    public String showTaskEditor(@PathVariable("id") Long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        return "taskEdit";
    }

    @PostMapping("{id}")
    @ApiOperation("Сохранить измененную задачу")
    public String saveEditTask(
            @RequestParam( required = false) String title,
            @RequestParam( required = false) String performer,
            @RequestParam( required = false) String releaseVersion,
            @RequestParam( required = false) Task.Status status,
            @PathVariable("id") Long id) {
        taskService.taskEditSave(title, performer, releaseVersion, id, status);
        return "redirect:/dashboard/" + taskService.getTaskProjectId(id);
    }

    @PostMapping("{id}/delete")
    public String deleteTask(
            @PathVariable("id") Long id) {
        Long projectid = taskService.getTaskProjectId(id);
        taskService.deleteTask(id);
        return "redirect:/dashboard/" + projectid;
    }
}

