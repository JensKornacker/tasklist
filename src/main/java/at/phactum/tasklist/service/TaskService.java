package at.phactum.tasklist.service;

import java.time.LocalDateTime;
import java.util.List;

import at.phactum.tasklist.domain.Task;
import at.phactum.tasklist.exception.InvalidDataException;
import at.phactum.tasklist.mapper.TaskMapper;
import at.phactum.tasklist.persistence.TaskRepo;
import at.phactum.tasklist.rest.AddAssignee;
import at.phactum.tasklist.rest.CompleteTaskEvent;
import at.phactum.tasklist.rest.TaskDto;
import at.phactum.tasklist.rest.TasklistDto;
import at.phactum.tasklist.rest.WorkflowUserTaskDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;

    public void saveTask(WorkflowUserTaskDto workflowUserTaskDto) {
        Task task = taskMapper.map(workflowUserTaskDto);
        task.setCreatedAt(LocalDateTime.now());
        try {
            taskRepo.save(task);
        } catch (Exception e) {
            throw new InvalidDataException(e.getMessage());
        }
    }

    public List<TasklistDto> listOfTasks() {
        return taskMapper.mapForList(taskRepo.findAllByCompletedAtIsNullOrderByCreatedAtDesc());
    }

    public TaskDto getTask(String taskId) {
        return taskMapper.mapToTaskDto(taskRepo.findByTaskId(taskId));
    }

    public void completeTask(CompleteTaskEvent completeTaskEvent) {
        final Task task = taskRepo.findByTaskId(completeTaskEvent.getTaskId());
        task.setCompletedAt(LocalDateTime.now());
        task.setStatus(completeTaskEvent.getStatus());
        taskRepo.save(task);
    }

    public AddAssignee addAssignee(AddAssignee addAssignee) {
        final Task task = taskRepo.findByTaskId(addAssignee.taskId());
        task.setAssignee(addAssignee.username());
        final TaskDto taskDto = taskMapper.mapToTaskDto(taskRepo.save(task));
        return new AddAssignee(taskDto.getAssignee(), taskDto.getTaskId());
    }

}
