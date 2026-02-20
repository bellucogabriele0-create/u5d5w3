package gabrielebelluco.u5d5w3.exception;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timestamp) {
}