package com.example.mytaskmanagersimbirsofr.service;

import com.example.mytaskmanagersimbirsofr.entity.Project;
import com.example.mytaskmanagersimbirsofr.repository.ProjectRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProjectService {
    private final ProjectRepo projectRepo;

    @Autowired
    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public List<Project> showProjects() {
        return projectRepo.findAll();
    }

    public void addProject(String name) {
        Project project = new Project(name);
        projectRepo.save(project);
        log.info("IN addProject - project: {} successfully added", name);
    }

    public Project getProjectById(Long id) {
        log.info("IN getProjectById - project: {} successfully found", id);
        return projectRepo.findById(id).get();

    }

    public void saveProject(Project project) {
        projectRepo.save(project);
        log.info("IN saveProject - project: {} successfully saved", project.getId());
    }

    public void projectEditSave(String name, Long id) {
        Project project = projectRepo.getById(id);
        if (!name.isEmpty()) {
            project.setName(name);
        }
        saveProject(project);
    }
}
