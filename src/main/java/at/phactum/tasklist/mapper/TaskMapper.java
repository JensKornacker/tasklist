package at.phactum.tasklist.mapper;

import java.util.List;
import java.util.Map;

import at.phactum.tasklist.domain.Task;
import at.phactum.tasklist.rest.TasklistUserTaskDto;
import at.phactum.tasklist.rest.WorkflowUserTaskDto;
import at.phactum.tasklist.utils.HashMapConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface TaskMapper {

    HashMapConverter hashmapConverter = new HashMapConverter();

    WorkflowUserTaskDto map(Task task);
    List<TasklistUserTaskDto> map(List<Task> taskList);

    Task map(WorkflowUserTaskDto workflowUserTaskDto);

    @Mapping(target = "additionalInfo", source = "additionalInfo", qualifiedByName = "mapToPojo")
    TasklistUserTaskDto mapToTaskList(Task task);

    @Named("mapToPojo")
    default Map<String, Object> mapToPojo(String additionalInfo) {
        return hashmapConverter.convertToEntityAttribute(additionalInfo);
    }
}
