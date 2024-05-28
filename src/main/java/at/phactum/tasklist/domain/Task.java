package at.phactum.tasklist.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TASKS")
@Data
public class Task {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "MODULE_ID")
    private String moduleId;

    @Column(name = "URL")
    private String url;

    @Column(name = "task_id")
    private String taskId;

    @Column(name = "additional_info")
    private String additionalInfo;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "complete_endpoint")
    private String completeEndpoint;

    @Column(name = "aggregate_id")
    private String aggregateId;

    @Column(name = "assignee")
    private String assignee;
}
