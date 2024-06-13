package at.phactum.tasklist.rest;

import java.time.LocalDate;
import java.util.Map;
import java.util.SortedMap;

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
    SortedMap<String, SortedMap<String, String>> additionalInfo;
    String aggregateId;
    String assignee;
    LocalDate createdAt;
    String status;
    Map<String, Object> config;
    Map<String, Object> configData;
    String taskDefinition;
}
