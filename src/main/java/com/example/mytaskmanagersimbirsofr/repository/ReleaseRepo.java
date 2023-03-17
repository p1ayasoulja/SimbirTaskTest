package com.example.mytaskmanagersimbirsofr.repository;

import com.example.mytaskmanagersimbirsofr.entity.Release;
import com.example.mytaskmanagersimbirsofr.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleaseRepo extends JpaRepository<Release, Long> {
    List<Release> findByTask(Task task);
}
