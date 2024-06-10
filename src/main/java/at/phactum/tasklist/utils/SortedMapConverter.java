package at.phactum.tasklist.utils;

import java.util.SortedMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import java.util.Collections;
import org.springframework.stereotype.Service;

@Service
public class SortedMapConverter implements AttributeConverter<SortedMap<String, SortedMap<String, String>>, String> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(final SortedMap<String, SortedMap<String, String>> stringSortedMapSortedMap) {
        try {
            return OBJECT_MAPPER.writeValueAsString(stringSortedMapSortedMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SortedMap<String, SortedMap<String, String>> convertToEntityAttribute(final String s) {
        if (s == null) {
            return Collections.emptySortedMap();
        }
        TypeReference<SortedMap<String, SortedMap<String, String>>> typeReference = new TypeReference<>() {};
        try {
            return OBJECT_MAPPER.readValue(s, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
