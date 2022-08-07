package pl.sda.arppl4.rental.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sda.arppl4.rental.exception.CarNotAvailableException;
import pl.sda.arppl4.rental.model.dto.ErrorMessageResponse;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestControllerAdvice
public class ErrorHandlingController {

    // Mapping
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> handleEntityNotFound(EntityNotFoundException exception) {
        // Ramka HTTP posiada:
        // - adres (ip:port/context/mapping)
        // - metoda http (POST, GET, PATCH, PUT, DELETE) (Request)
        // - nagłówki (headers)
        // - body<>
        // - status (odpowiedź / response)
        return ResponseEntity

                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessageResponse(exception.getMessage()));
    }

    @ExceptionHandler(CarNotAvailableException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseStatus(HttpStatus.GONE)
    public ErrorMessageResponse handleCarNotAvailable(CarNotAvailableException exception){
        return new ErrorMessageResponse(exception.getMessage());
    }
}
