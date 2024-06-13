package at.phactum.tasklist.rest;

import lombok.Data;

@Data
public class WorkflowUserTaskDto {
    private String taskId;
    private String title;
    private String description;
    private String moduleId;
    private String url;
    private String completeEndpoint;
    private String additionalInfo;
    private String aggregateId;
    private String status;
    private String config;
    private String configData;
    private String taskDefinition;
}
