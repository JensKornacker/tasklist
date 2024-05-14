package at.phactum.tasklist.rest;

import lombok.Data;

@Data
public class TaskDto {
    private String taskId;
    private String title;
    private String description;
    private String moduleId;
    private String url;
}
