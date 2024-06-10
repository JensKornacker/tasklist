package at.phactum.tasklist.rest;

import java.util.List;

import at.phactum.tasklist.exception.InvalidDataException;
import at.phactum.tasklist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<List<TasklistUserTaskDto>> listOfTasks() {
        final List<TasklistUserTaskDto> dtoList = taskService.listOfTasks();
        return ResponseEntity.ok()
                             .body(dtoList);
    }

    @PostMapping
    public ResponseEntity<Void> saveTask(@RequestBody WorkflowUserTaskDto workflowUserTaskDto) {
        taskService.saveTask(workflowUserTaskDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("complete")
    public ResponseEntity<Void> completeTask(@RequestBody CompleteTaskEvent completeTaskEvent) {
        taskService.completeTask(completeTaskEvent);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{taskId}")
    public ResponseEntity<TasklistUserTaskDto> getTask(@PathVariable("taskId") String taskId) {
        return ResponseEntity.ok()
                             .header("Access-Control-Allow-Origin", "http://localhost:4200")
                             .body(taskService.getTask(taskId));
    }

    @PostMapping("add-assignee")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<TasklistUserTaskDto> addAssignee(@RequestBody AddAssignee addAssignee) {
        return ResponseEntity.ok().body(taskService.addAssignee(addAssignee));
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Void> handleInvalidData(InvalidDataException invalidDataException) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                             .build();
    }

}
