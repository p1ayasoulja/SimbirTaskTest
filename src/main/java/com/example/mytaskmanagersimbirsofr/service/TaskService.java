package com.example.mytaskmanagersimbirsofr.service;

import com.example.mytaskmanagersimbirsofr.entity.Project;
import com.example.mytaskmanagersimbirsofr.entity.Task;
import com.example.mytaskmanagersimbirsofr.repository.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public void addTask(String title, String author, String performer, Project id) {
        Task task = new Task(title, author, performer, id);
        taskRepo.save(task);
    }

    public List<Task> showTaskList(Project id) {
        if (id != null) {
            return taskRepo.findByDashboard(id);
        } else
            return taskRepo.findAll();
    }

    public void saveTask(Task task) {
        taskRepo.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepo.findById(id).get();
    }
    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }


}
