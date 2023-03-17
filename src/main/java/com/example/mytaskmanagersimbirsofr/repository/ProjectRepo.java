package com.example.mytaskmanagersimbirsofr.repository;

import com.example.mytaskmanagersimbirsofr.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
}
