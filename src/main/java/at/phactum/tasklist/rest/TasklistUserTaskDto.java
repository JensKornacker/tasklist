package at.phactum.tasklist.rest;

import java.time.LocalDate;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TasklistUserTaskDto {
    String taskId;
    String title;
    String description;
    String moduleId;
    String url;
    String completeEndpoint;
    Map<String, Object> additionalInfo;
    String aggregateId;
    String assignee;
    LocalDate createdAt;
}
