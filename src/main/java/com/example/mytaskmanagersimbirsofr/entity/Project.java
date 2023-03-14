package com.example.mytaskmanagersimbirsofr.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "dashboard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Task> tasks;
    @Column(name = "is_closed")
    private boolean is_closed;

    public Project() {
    }

    public Project(String name) {

        this.name = name;
        this.is_closed = false;
    }
}
