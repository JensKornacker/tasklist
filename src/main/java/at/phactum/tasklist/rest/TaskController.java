package at.phactum.tasklist.rest;

import at.phactum.tasklist.domain.Task;
import at.phactum.tasklist.exception.InvalidDataException;
import at.phactum.tasklist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Void> saveTask(@RequestBody TaskDto taskDto) {
        taskService.saveTask(taskDto);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Void> handleInvalidData(InvalidDataException invalidDataException) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                             .build();
    }

}
