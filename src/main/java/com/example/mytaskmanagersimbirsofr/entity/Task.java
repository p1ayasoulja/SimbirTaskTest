package com.example.mytaskmanagersimbirsofr.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "performer")
    private String performer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dashboard")
    private Project dashboard;
    @Column(name = "Status")
    private Status status;
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Release> releases;

    public Task() {
    }

    public Task(String title, String author, String performer, Project id) {
        this.title = title;
        this.author = author;
        this.performer = performer;
        this.status = Status.IN_PROGRESS;
        this.dashboard = id;

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public Project getDashboard() {
        return dashboard;
    }

    public void setDashboard(Project dashboard) {
        this.dashboard = dashboard;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Release> getReleases() {
        return releases;
    }

    public void setReleases(List<Release> releases) {
        this.releases = releases;
    }

    public enum Status {
        BACKLOG, DONE, IN_PROGRESS
    }
}