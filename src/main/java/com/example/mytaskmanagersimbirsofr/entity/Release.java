package com.example.mytaskmanagersimbirsofr.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;
}
