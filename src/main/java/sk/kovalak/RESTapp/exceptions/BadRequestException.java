package sk.kovalak.RESTapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Bad request")  // 400
public class BadRequestException extends RuntimeException {
}
