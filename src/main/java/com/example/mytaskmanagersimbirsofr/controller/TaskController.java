package com.example.mytaskmanagersimbirsofr.controller;

import com.example.mytaskmanagersimbirsofr.repository.TaskRepo;
import com.example.mytaskmanagersimbirsofr.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
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
    public String taskEditSave(
            @RequestParam String title,
            @RequestParam String performer,
            @RequestParam String releaseVersion,
            @PathVariable("id") Long id) {
        taskService.taskEditSave(title, performer, releaseVersion, id);
        return "redirect:/dashboard/" + taskService.getTaskProjectId(id);
    }

//    @PostMapping("{id}/delete")
//    public String taskDelete(
//            @PathVariable("id") Long id) {
//        taskService.taskDelete(id);
//        return "redirect:/dashboard/" + taskService.getProjectId(id);
//    }
}
