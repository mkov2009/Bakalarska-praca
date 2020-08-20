package sk.kovalak.RESTapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Data not found")  // 404
public class DataNotFoundException extends RuntimeException {

}
