package com.example.mytaskmanagersimbirsofr.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task")
    private Task task;
    @Column(name = "version")
    private String version;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;


    public Release() {
    }

    public Release(String version, LocalDateTime startTime, Task task) {
        this.version = version;
        this.startTime = startTime;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String releaseVersion) {
        this.version = releaseVersion;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
