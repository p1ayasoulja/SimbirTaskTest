package com.example.mytaskmanagersimbirsofr.service;

import com.example.mytaskmanagersimbirsofr.entity.Project;
import com.example.mytaskmanagersimbirsofr.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepo projectRepo;

    @Autowired
    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    public List<Project> showProjects(){
       return projectRepo.findAll();
    }

    public void addProject(String name){
        Project project = new Project(name);
        projectRepo.save(project);
    }
    public Project getProjectById(Long id) {
        return projectRepo.findById(id).get();
    }
    public void saveProject(Project project) {
        projectRepo.save(project);
    }
}
