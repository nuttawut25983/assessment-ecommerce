package maze.lucablock.assessmentecommerce.exceptions;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


public class ApiErrorResponse {
    private final String message;

    private final HttpStatus code;

    private final ZonedDateTime dateTime;

    public ApiErrorResponse(String message, HttpStatus code, ZonedDateTime dateTime) {
        this.message = message;
        this.code = code;
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getCode() {
        return code;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }



}
