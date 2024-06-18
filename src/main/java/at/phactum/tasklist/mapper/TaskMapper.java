package at.phactum.tasklist.mapper;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import at.phactum.tasklist.domain.Task;
import at.phactum.tasklist.rest.TaskDto;
import at.phactum.tasklist.rest.TasklistDto;
import at.phactum.tasklist.rest.WorkflowUserTaskDto;
import at.phactum.tasklist.utils.DataItemConverter;
import at.phactum.tasklist.utils.HashMapConverter;
import at.phactum.tasklist.utils.SortedMapConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface TaskMapper {

    HashMapConverter hashmapConverter = new HashMapConverter();
    SortedMapConverter sortedMapConverter = new SortedMapConverter();
    DataItemConverter dataItemConverter = new DataItemConverter();

    WorkflowUserTaskDto map(Task task);
    List<TaskDto> map(List<Task> taskList);

    List<TasklistDto> mapForList(List<Task> taskList);

    Task map(WorkflowUserTaskDto workflowUserTaskDto);

    @Mapping(target = "additionalInfo", source = "additionalInfo", qualifiedByName = "mapToPojo")
    @Mapping(target = "config", source = "config", qualifiedByName = "mapConfig")
    @Mapping(target = "configData", source = "configData", qualifiedByName = "mapConfig")
    TaskDto mapToTaskDto(Task task);


    @Mapping(target = "customerName", source = "additionalInfo", qualifiedByName = "extractCustomerName")
    TasklistDto mapToTasklistDto(Task task);

    @Named("mapToPojo")
    default SortedMap<String, SortedMap<String, String>> mapToPojo(String additionalInfo) {
        return sortedMapConverter.convertToEntityAttribute(additionalInfo);
    }

    @Named("mapConfig")
    default Map<String, Object> mapConfig(String config) {
        return hashmapConverter.convertToEntityAttribute(config);
    }

    @Named("extractCustomerName")
    default String extractCustomerName(String additionalInfo) {
        final SortedMap<String, SortedMap<String, String>> stringSortedMapSortedMap = sortedMapConverter.convertToEntityAttribute(additionalInfo);
        final SortedMap<String, String> customer = stringSortedMapSortedMap.get("customer");
        return customer.get("Name");
    }

}
