package guru.springframework.errorhandling.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Could not process the request. Please try again later.")
public class FetchAllInternalException extends RuntimeException {

    public FetchAllInternalException() {
    }
}
