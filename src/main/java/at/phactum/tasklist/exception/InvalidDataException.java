package at.phactum.tasklist.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class InvalidDataException extends RuntimeException {
    private final String message;
}
