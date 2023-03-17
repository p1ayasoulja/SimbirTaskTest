package com.example.mytaskmanagersimbirsofr.controller;

import com.example.mytaskmanagersimbirsofr.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
@PreAuthorize("hasAuthority('MANAGER')")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("{id}")
    @ApiOperation("Редактирование проекта")
    public String showProjectEditor(@PathVariable("id") Long id, Model model) {
        model.addAttribute("project", projectService.getProjectById(id));
        return "projectEditor";
    }

    @PostMapping("{id}")
    @ApiOperation("Сохранение редактированного проекта")
    public String updateProject(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean closed,
            @PathVariable("id") Long id
    ) {
        projectService.updateProject(name, id, closed);
        return "redirect:/dashboard";
    }
}