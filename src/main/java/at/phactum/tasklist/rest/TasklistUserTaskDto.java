package at.phactum.tasklist.rest;

import java.util.Map;

import lombok.Data;

@Data
public class TasklistUserTaskDto {
    private String taskId;
    private String title;
    private String description;
    private String moduleId;
    private String url;
    private String completeEndpoint;
    private Map<String, Object> additionalInfo;
    private String aggregateId;
}
