package cz.fel.cvut.hamrasan.gardener.rest.handler;

import cz.fel.cvut.hamrasan.gardener.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

    private static void logException(RuntimeException ex) {
        LOG.error("Exception caught:", ex);
    }

    private static ErrorInfo errorInfo(HttpServletRequest request, Throwable e) {
        return new ErrorInfo(e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorInfo> resourceNotFound(HttpServletRequest request, NotFoundException e) {
        return new ResponseEntity<>(errorInfo(request, e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorInfo> resourceAlreadyExistsFound(HttpServletRequest request, AlreadyExistsException e) {
        return new ResponseEntity<>(errorInfo(request, e), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotAllowedException.class)
    public ResponseEntity<ErrorInfo> notAllowed(HttpServletRequest request, NotAllowedException e) {
        return new ResponseEntity<>(errorInfo(request, e), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AlreadyLoginException.class)
    public ResponseEntity<ErrorInfo> alreadyLogin(HttpServletRequest request, AlreadyLoginException e) {
        return new ResponseEntity<>(errorInfo(request, e), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(BadPassword.class)
    public ResponseEntity<ErrorInfo> badPassword(HttpServletRequest request, BadPassword e) {
        return new ResponseEntity<>(errorInfo(request, e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorInfo> unauthorized(HttpServletRequest request, UnauthorizedException e) {
        return new ResponseEntity<>(errorInfo(request, e), HttpStatus.UNAUTHORIZED);
    }

}
