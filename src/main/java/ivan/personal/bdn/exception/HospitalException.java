package ivan.personal.bdn.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

@Getter
public class HospitalException extends HttpStatusCodeException {
    public HospitalException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
