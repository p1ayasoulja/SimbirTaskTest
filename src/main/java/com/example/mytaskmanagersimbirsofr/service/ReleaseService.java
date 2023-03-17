package com.example.mytaskmanagersimbirsofr.service;

import com.example.mytaskmanagersimbirsofr.entity.Release;
import com.example.mytaskmanagersimbirsofr.entity.Task;
import com.example.mytaskmanagersimbirsofr.repository.ReleaseRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ReleaseService {
    private final ReleaseRepo releaseRepo;

    public ReleaseService(ReleaseRepo releaseRepo) {
        this.releaseRepo = releaseRepo;
    }

    /**
     *
     * @param version версия релиза проекта
     * @param task сущность задачи
     */
    public void addRelease(String version, Task task) {
        List<Release> releaseList = releaseRepo.findByTask(task);
        if (releaseList.size() != 0) {
            Release release = releaseList.get(releaseList.size() - 1);
            release.setEndTime(LocalDateTime.now());
            releaseRepo.save(release);
        }
        Release releaseCurrent = new Release(version, LocalDateTime.now(), task);
        releaseRepo.save(releaseCurrent);
    }

    /**
     *
     * @param id идентификатор задачи
     * @return список версий релиза проекта
     */
    public List<Release> getReleaseByTask(Task id) {
        return releaseRepo.findByTask(id);
    }

    /**
     *
     * @param task сущность задачи
     */
    public void deleteReleases(Task task) {
        List<Release> releases = releaseRepo.findByTask(task);
        releaseRepo.deleteAll(releases);
    }
}
