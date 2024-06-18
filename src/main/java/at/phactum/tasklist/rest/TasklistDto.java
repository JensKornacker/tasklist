package at.phactum.tasklist.rest;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TasklistDto {
    String taskId;
    String title;
    String description;
    String moduleId;
    String customerName;
    String aggregateId;
    String assignee;
    LocalDate createdAt;
    String status;
    String taskDefinition;
}
