package finki.ukim.mk.emtlab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(Long id) {
        super(String.format("Item with id: %d is not found", id));
    }
}
