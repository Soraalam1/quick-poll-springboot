package com.quickpoll.handler;

import com.quickpoll.dto.error.ErrorDetail;
import com.quickpoll.dto.error.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(status.value());
        errorDetail.setTitle("Message Not Readable");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setDeveloperMessage(ex.getClass().getName());

        return handleExceptionInternal(ex, errorDetail, headers, status, request);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request){
        ErrorDetail errorDetail = new ErrorDetail();

        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());

        //These lines needed to be commented out because they don't work once we @Override and change parameters
//        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
//        if(requestPath == null){
//            requestPath = request.getRequestURI();
//        }
        errorDetail.setTitle("Validation Failed");
        errorDetail.setTitle("Input Validation failed");
        errorDetail.setDeveloperMessage(e.getClass().getName());

        //Create Validation Error instances (the map)
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError: fieldErrors) {
            List<ValidationError> validationErrorList = errorDetail.getErrors()
                    .get(fieldError.getField());

            if(validationErrorList == null){
                validationErrorList = new ArrayList<ValidationError>();
                errorDetail.getErrors().put(fieldError.getField(), validationErrorList);
            }
            ValidationError validationError = new ValidationError();
            validationError.setCode(fieldError.getCode());
            validationError.setMessage(messageSource.getMessage(fieldError, null));
            validationErrorList.add(validationError);
        }
        return handleExceptionInternal(e, errorDetail, headers, status, request);
    }

    //WE DELETED THIS NEXT ONE AFTER MAKING handleValidationError()

    //    @ExceptionHandler(ResourceNotFoundException.class)
//    @ResponseBody
//    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest requrest){
//        ErrorDetail errorDetail = new ErrorDetail();
//        errorDetail.setTimeStamp(new Date().getTime());
//        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
//        errorDetail.setTitle("Resource Not Found");
//        errorDetail.setDetail(rnfe.getMessage());
//        errorDetail.setDeveloperMessage(rnfe.getClass().getName());
//
//        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
//    }
}
