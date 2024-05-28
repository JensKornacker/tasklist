package at.phactum.tasklist.service;

import java.time.LocalDateTime;
import java.util.List;

import at.phactum.tasklist.domain.Task;
import at.phactum.tasklist.exception.InvalidDataException;
import at.phactum.tasklist.mapper.TaskMapper;
import at.phactum.tasklist.persistence.TaskRepo;
import at.phactum.tasklist.rest.AddAssignee;
import at.phactum.tasklist.rest.CompleteTaskEvent;
import at.phactum.tasklist.rest.TasklistUserTaskDto;
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

    public List<TasklistUserTaskDto> listOfTasks() {
        return taskMapper.map(taskRepo.findAllByCompletedAtIsNull());
    }

    public TasklistUserTaskDto getTask(String taskId) {
        return taskMapper.mapToTaskList(taskRepo.findByTaskId(taskId));
    }

    public void completeTask(CompleteTaskEvent completeTaskEvent) {
        final Task task = taskRepo.findByTaskId(completeTaskEvent.getTaskId());
        task.setCompletedAt(LocalDateTime.now());
        taskRepo.save(task);
    }

    public TasklistUserTaskDto addAssignee(AddAssignee addAssignee) {
        final Task task = taskRepo.findByTaskId(addAssignee.taskId());
        task.setAssignee(addAssignee.username());
        return taskMapper.mapToTaskList(taskRepo.save(task));
    }

}
