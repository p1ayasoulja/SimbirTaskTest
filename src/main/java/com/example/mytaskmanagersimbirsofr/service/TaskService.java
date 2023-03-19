package com.example.mytaskmanagersimbirsofr.service;

import com.example.mytaskmanagersimbirsofr.entity.Project;
import com.example.mytaskmanagersimbirsofr.entity.Task;
import com.example.mytaskmanagersimbirsofr.repository.ReleaseRepo;
import com.example.mytaskmanagersimbirsofr.repository.TaskRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TaskService {
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo, ReleaseRepo releaseRepo) {
        this.taskRepo = taskRepo;
    }

    /**
     * Показ задач проекта
     *
     * @param id - идентификатор проекта
     * @return список задач на проекте
     **/
    public List<Task> getTaskList(Project id) {
        log.info("IN showTaskList - tasks of: {} project successfully showed", id.getId());
        return taskRepo.findByDashboard(id);

    }

    /**
     * Сохранение проекта
     *
     * @param task - сущность проекта
     **/
    public void saveTask(Task task) {
        taskRepo.save(task);
    }

    /**
     * Получение задачи по ее идентификатору
     *
     * @param id - идентификатор задачи
     * @return сущность задачи
     **/
    public Task getTaskById(Long id) {
        log.info("IN getTaskById - task: {} successfully found", id);
        return taskRepo.findById(id).get();
    }

    /**
     * Удаление задачи по идентификатору
     *
     * @param id - идентификатор задачи
     **/
    public void deleteTask(Long id) {
        log.info("IN deleteTask - task: {} successfully deleted", id);
        taskRepo.deleteById(id);
    }

    /**
     * Сохранение измененной задачи
     *
     * @param title     - описание задачи
     * @param performer - идентификатор проекта
     * @param id        - индентификатор задачи
     * @param status    -  статус выполнения задачи
     **/
    public void updateTask(String title, String performer, Long id, Task.Status status) {
        Task task = taskRepo.getById(id);
        if (!title.isEmpty()) {
            task.setTitle(title);
        }
        if (!performer.isEmpty()) {
            task.setPerformer(performer);
        }
        if (status != null) {
            task.setStatus(status);
        }
        saveTask(task);
        log.info("IN taskEditSave - task: {} successfully edited", id);
    }

    /**
     * Получение идентификатора проекта по идентификатору задачи
     *
     * @param id - идентификатор задачи
     * @return идентификатор проекта
     **/
    public Long getTaskProjectId(Long id) {
        Task task = taskRepo.getById(id);
        Project project = task.getDashboard();
        log.info("IN getTaskProjectId - task project Id : {} successfully found", project.getId());
        return project.getId();

    }

}
