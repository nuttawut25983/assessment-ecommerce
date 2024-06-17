package maze.lucablock.assessmentecommerce.exceptions;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.UNAUTHORIZED)  // กำหนด HTTP Status code ตามที่ต้องการ
  @ExceptionHandler(CustomAuthenticationException.class)
  public ResponseEntity<Object> handleCustomAuthenticationException(CustomAuthenticationException ex) {
    ApiErrorResponse apiExceptionResponse = new ApiErrorResponse(
        ex.getMessage(),
        HttpStatus.UNAUTHORIZED,
        ZonedDateTime.now()
    );
    return new ResponseEntity<>(apiExceptionResponse, HttpStatus.UNAUTHORIZED);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)  // กำหนด HTTP Status code สำหรับ NOT_FOUND
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> handleIllegalArgumentException(NotFoundException ex) {
    ApiErrorResponse apiExceptionResponse = new ApiErrorResponse(
        ex.getMessage(),
        HttpStatus.NOT_FOUND,
        ZonedDateTime.now()
    );
    return new ResponseEntity<>(apiExceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(InternalServerException.class)
  public ResponseEntity<Object> handleException(Exception ex) {
    ApiErrorResponse apiExceptionResponse = new ApiErrorResponse(
        ex.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR,
        ZonedDateTime.now()
    );
    return new ResponseEntity<>(apiExceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
    ApiErrorResponse apiExceptionResponse = new ApiErrorResponse(
        ex.getMessage(),
        HttpStatus.BAD_REQUEST,
        ZonedDateTime.now()
    );
    return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
  }


}

