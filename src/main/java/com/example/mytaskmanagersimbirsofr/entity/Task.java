package com.example.mytaskmanagersimbirsofr.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Task {
    public enum Status {
        BACKLOG, DONE, IN_PROGRESS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "performer")
    private String performer;
    @Column(name = "release_version")
    private String releaseVersion;
    @Column(name = "start_time")
    private LocalDate startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dashboard")
    private Project dashboard;
    @Column(name = "Status")
    private Status status;

    public Task() {
    }

    public Task(String title, String author, String performer, Project id) {
        this.title = title;
        this.author = author;
        this.performer = performer;
        this.releaseVersion = "1.0";
        this.startTime = LocalDate.now();
        this.status=Status.IN_PROGRESS;
        this.dashboard =id;

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPerformer() {
        return performer;
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Project getDashboard() {
        return dashboard;
    }

    public Status getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStatus(Status status) {this.status = status;
    }
}