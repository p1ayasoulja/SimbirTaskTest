package com.example.mytaskmanagersimbirsofr.controller;

import com.example.mytaskmanagersimbirsofr.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
@PreAuthorize("hasAuthority('MANAGER')")
public class ProjectController {
    @Autowired

    private ProjectService projectService;

    @GetMapping("{id}")
    @ApiOperation("Редактирование проекта")
    public String projectEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("project", projectService.getProjectById(id));
        return "projectEdit";
    }

    @PostMapping("{id}")
    @ApiOperation("Сохранение редактированного проекта")
    public String projectEditSave(
            @RequestParam String name,
            @PathVariable("id") Long id) {
        projectService.projectEditSave(name, id);
        return "redirect:/dashboard";
    }
}