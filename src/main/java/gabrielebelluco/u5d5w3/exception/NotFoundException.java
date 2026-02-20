package gabrielebelluco.u5d5w3.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Risorsa con id " + id + " non trovata!");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
