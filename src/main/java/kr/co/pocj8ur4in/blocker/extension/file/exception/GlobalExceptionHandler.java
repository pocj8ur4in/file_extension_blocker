package kr.co.pocj8ur4in.blocker.extension.file.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateExtensionException.class)
    public ResponseEntity<String> handleDuplicateExtension(DuplicateExtensionException error) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error.getMessage());
    }

    @ExceptionHandler(ExistDefaultExtensionException.class)
    public ResponseEntity<String> handleExistDefaultExtension(
            ExistDefaultExtensionException error) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error.getMessage());
    }

    @ExceptionHandler(ExtensionNotFoundException.class)
    public ResponseEntity<String> handleExtensionNotFound(ExtensionNotFoundException error) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
    }

    @ExceptionHandler(MaxExtensionLimitException.class)
    public ResponseEntity<String> handleMaxExtensionLimit(MaxExtensionLimitException error) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException error) {
        return ResponseEntity.internalServerError().body(error.getMessage());
    }
}
