package sk.kovalak.RESTapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Sensor not found")  // 404
public class SensorNotFoundException extends RuntimeException{
}
