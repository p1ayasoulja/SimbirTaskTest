package com.example.mytaskmanagersimbirsofr.service;

import com.example.mytaskmanagersimbirsofr.entity.Project;
import com.example.mytaskmanagersimbirsofr.entity.Task;
import com.example.mytaskmanagersimbirsofr.repository.TaskRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public void addTask(String title, String author, String performer, Project id) {
        Task task = new Task(title, author, performer, id);
        taskRepo.save(task);
        log.info("IN addTask - task: {} successfully added", task.getId());
    }

    public List<Task> showTaskList(Project id) {
        if (id != null) {
            return taskRepo.findByDashboard(id);
        } else
            return taskRepo.findAll();
    }

    public void saveTask(Task task) {
        taskRepo.save(task);
        log.info("IN taskEditSave - task: {} successfully saved", task.getId());
    }

    public Task getTaskById(Long id) {
        log.info("IN getTaskById - task: {} successfully found", id);
        return taskRepo.findById(id).get();
    }

    public void deleteTask(Long id) {
        log.info("IN deleteTask - task: {} successfully deleted", id);
        taskRepo.deleteById(id);
    }

    public void taskEditSave(String title, String performer, String releaseVersion, Long id) {
        Task task = taskRepo.getById(id);
        if (!title.isEmpty()) {
            task.setTitle(title);
        }
        if (!performer.isEmpty()) {
            task.setPerformer(performer);
        }
        if (!releaseVersion.isEmpty()) {
            task.setReleaseVersion(releaseVersion);
        }
        saveTask(task);
    }

    public Long getTaskProjectId(Long id) {
        Task task = taskRepo.getById(id);
        Project project = task.getDashboard();
        log.info("IN getTaskProjectId - task project Id : {} successfully found", project.getId());
        return project.getId();

    }

}
