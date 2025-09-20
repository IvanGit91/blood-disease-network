package ivan.personal.bdn.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

@Getter
public class AppException extends HttpStatusCodeException {
    public AppException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
