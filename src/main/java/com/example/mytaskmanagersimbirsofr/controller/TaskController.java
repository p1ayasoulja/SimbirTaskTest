package com.example.mytaskmanagersimbirsofr.controller;

import com.example.mytaskmanagersimbirsofr.entity.Release;
import com.example.mytaskmanagersimbirsofr.entity.Task;
import com.example.mytaskmanagersimbirsofr.service.ReleaseService;
import com.example.mytaskmanagersimbirsofr.service.TaskService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
    private final ReleaseService releaseService;

    public TaskController(TaskService taskService, ReleaseService releaseService) {
        this.taskService = taskService;
        this.releaseService = releaseService;
    }

    @GetMapping("{id}")
    @ApiOperation("Метод редактирования задачи")
    public String showTaskEditor(@PathVariable("id") Long id, Model model, Map<String, Object> modelMap) {
        Task task = taskService.getTaskById(id);
        List<Release> releases = releaseService.getReleaseByTask(task);
        modelMap.put("release", releases);
        model.addAttribute("release", releases);
        model.addAttribute("task", task);
        return "taskEditor";
    }

    @PostMapping("{id}")
    @ApiOperation("Обновить задачу")
    public String updateTask(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String performer,
            @RequestParam String version,
            @RequestParam(required = false) Task.Status status,
            @PathVariable("id") Long id
    ) {
        taskService.updateTask(title, performer, id, status);
        releaseService.addRelease(version, taskService.getTaskById(id));
        return "redirect:/dashboard/" + taskService.getTaskProjectId(id);
    }

    @PostMapping("{id}/delete")
    @ApiOperation("Удалить задачу")
    public String deleteTask(
            @PathVariable("id") Long id
    ) {
        Long project_id = taskService.getTaskProjectId(id);
        releaseService.deleteReleases(taskService.getTaskById(id));
        taskService.deleteTask(id);
        return "redirect:/dashboard/" + project_id;
    }
}

