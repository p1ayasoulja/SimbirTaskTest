package com.example.mytaskmanagersimbirsofr.service;

import com.example.mytaskmanagersimbirsofr.entity.Project;
import com.example.mytaskmanagersimbirsofr.entity.Task;
import com.example.mytaskmanagersimbirsofr.repository.ProjectRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProjectService {
    private final ProjectRepo projectRepo;

    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    /**
     * Показ всех проектов
     **/
    public List<Project> showProjects() {
        return projectRepo.findAll();
    }

    /**
     * Добавление нового проекта в базу данных
     *
     * @param name - Имя проекта
     **/
    public void addProject(String name) {
        Project project = new Project(name, false);
        projectRepo.save(project);
        log.info("IN addProject - project: {} successfully added", name);
    }

    /**
     * Получение проекта по его идентификатору
     *
     * @param id - Идентификатор проекта
     * @return сущность проекта
     **/
    public Project getProjectById(Long id) {
        log.info("IN getProjectById - project: {} successfully found", id);
        return projectRepo.findById(id).get();

    }

    /**
     * Сохранение проекта
     *
     * @param project - Сущность проекта
     **/
    public void saveProject(Project project) {
        projectRepo.save(project);
    }

    /**
     * Сохранение измененного проекта
     *
     * @param name   Имя проекта
     * @param id     идентификатор проекта
     * @param closed статус завершения проекта
     **/
    public void updateProject(String name, Long id, Boolean closed) {
        Project project = projectRepo.getById(id);
        if (!name.isEmpty()) {
            project.setName(name);
            log.info("IN saveProject - name: {} successfully saved", name);
        }
        if (closed) {
            if (canBeProjectClose(projectRepo.getById(id))) {
                project.setClosed(true);
                log.info("IN saveProject - status: {} successfully saved", closed);
            }
        }
        else project.setClosed(false);
        saveProject(project);

    }

    /**
     * Проверка может ли проект быть закрыт
     *
     * @param project - сущность проекта
     * @return isDone - может ли проект быть закрыт
     **/
    public boolean canBeProjectClose(Project project) {
        List<Task> taskList = project.getTasks();
        boolean isDone = true;
        for (Task task : taskList) {
            if (task.getStatus() != Task.Status.DONE) {
                isDone = false;
                break;
            }
        }
        return isDone;
    }
}
