package at.phactum.tasklist.service;

import java.time.LocalDateTime;

import at.phactum.tasklist.domain.Task;
import at.phactum.tasklist.persistence.TaskRepo;
import at.phactum.tasklist.rest.TaskDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepo taskRepo;

    public void saveTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTaskId(taskDto.getTaskId());
        task.setTitle(taskDto.getTitle());
        task.setDescription(task.getDescription());
        task.setModuleId(task.getModuleId());
        task.setUrl(taskDto.getUrl());
        task.setCreatedAt(LocalDateTime.now());
        taskRepo.save(task);
    }

}
