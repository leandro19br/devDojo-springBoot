package br.com.devdojo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*extends uma exeception não checada*/
@ResponseStatus(HttpStatus.NOT_FOUND)//status será 404
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
