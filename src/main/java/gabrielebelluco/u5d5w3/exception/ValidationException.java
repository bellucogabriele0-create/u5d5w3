package gabrielebelluco.u5d5w3.exception;

import lombok.Getter;

import java.util.List;


@Getter
public class ValidationException extends RuntimeException {
    private List<String> errorsMessages;

    public ValidationException(List<String> errorsMessages) {
        super("errore riscontrato nel payload");
        this.errorsMessages = errorsMessages;
    }
}
