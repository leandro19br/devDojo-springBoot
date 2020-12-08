package br.com.devdojo.handler;

import br.com.devdojo.error.ErrorDetails;
import br.com.devdojo.error.ResourceNotFoundDetails;
import br.com.devdojo.error.ResourceNotFoundException;
import br.com.devdojo.error.ValidationErrorDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/*extends ResponseEntityExceptionHandler onde contém todas as exeptions e poderá ser feito a sobescrita do método*/

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundExeption(ResourceNotFoundException rfnException) {

        final ResourceNotFoundDetails resourceNotFoundDetails = ResourceNotFoundDetails.Builder
                .newBuilder().timesTamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resouce not Found")
                .detail(rfnException.getMessage())
                .developerMessage(rfnException.getClass().getName())
                .build();
        return new ResponseEntity<>(resourceNotFoundDetails, HttpStatus.NOT_FOUND);
    }

    /*subescrita do método handleExceptionInternal para criar uma mensagem para as exeption generico */

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body,
            HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        ErrorDetails errorDetails = ErrorDetails.Builder
                .newBuilder().timesTamp(new Date().getTime())
                .status(status.value())
                .title("Internal Exception")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();
        return new ResponseEntity<>(errorDetails, headers, status);

    }

}
