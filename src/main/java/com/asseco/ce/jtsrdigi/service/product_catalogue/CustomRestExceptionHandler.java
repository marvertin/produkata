package com.asseco.ce.jtsrdigi.service.product_catalogue;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.asseco.ce.jtsrdigi.service.product_catalogue.model.CommonResponseType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.GetProductDetailResponseType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.WarningErrorParamsType;
import com.asseco.ce.jtsrdigi.service.product_catalogue.model.WarningErrorType;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle MethodArgumentNotValidException
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        GetProductDetailResponseType getProductDetailResponseType = new GetProductDetailResponseType();
        CommonResponseType commonResponseType = new CommonResponseType();
        WarningErrorParamsType warningErrorParamsType = new WarningErrorParamsType();

        WarningErrorType warningErrorType = new WarningErrorType();
        warningErrorType.setCode("666.999.111");
        warningErrorType.setDescription("Handle MethodArgumentNotValidException :: " + errors);
        warningErrorType.setType(HttpStatus.BAD_REQUEST.toString());

        warningErrorParamsType.setWarningError(warningErrorType);
        commonResponseType.setErrorsAndWarnings(warningErrorParamsType);
        getProductDetailResponseType.setParams(commonResponseType);

        if (log.isDebugEnabled()) {
            log.debug("Handle MethodArgumentNotValidException :: {}\n---------\n{}\n---------\n", ex.getLocalizedMessage(), printStackTrace(ex.getStackTrace()));
        }

        return handleExceptionInternal(
                ex, getProductDetailResponseType, headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handle MissingServletRequestParameterException
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";

        GetProductDetailResponseType getProductDetailResponseType = new GetProductDetailResponseType();
        CommonResponseType commonResponseType = new CommonResponseType();
        WarningErrorParamsType warningErrorParamsType = new WarningErrorParamsType();

        WarningErrorType warningErrorType = new WarningErrorType();
        warningErrorType.setCode("666.999.222");
        warningErrorType.setDescription("Handle MissingServletRequestParameterException :: " + error);
        warningErrorType.setType(HttpStatus.BAD_REQUEST.toString());

        warningErrorParamsType.setWarningError(warningErrorType);
        commonResponseType.setErrorsAndWarnings(warningErrorParamsType);
        getProductDetailResponseType.setParams(commonResponseType);

        if (log.isDebugEnabled()) {
            log.debug("Handle MissingServletRequestParameterException :: {}\n---------\n{}\n---------\n", ex.getLocalizedMessage(), printStackTrace(ex.getStackTrace()));
        }

        return new ResponseEntity<Object>(
                getProductDetailResponseType, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle ConstrainViolationException
     */
    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {

        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        GetProductDetailResponseType getProductDetailResponseType = new GetProductDetailResponseType();
        CommonResponseType commonResponseType = new CommonResponseType();
        WarningErrorParamsType warningErrorParamsType = new WarningErrorParamsType();

        WarningErrorType warningErrorType = new WarningErrorType();
        warningErrorType.setCode("666.999.333");
        warningErrorType.setDescription("Handle ConstrainViolationException :: " + errors);
        warningErrorType.setType(HttpStatus.BAD_REQUEST.toString());

        warningErrorParamsType.setWarningError(warningErrorType);
        commonResponseType.setErrorsAndWarnings(warningErrorParamsType);
        getProductDetailResponseType.setParams(commonResponseType);

        if (log.isDebugEnabled()) {
            log.debug("Handle ConstrainViolationException :: {}\n---------\n{}\n---------\n", ex.getLocalizedMessage(), printStackTrace(ex.getStackTrace()));
        }

        return new ResponseEntity<Object>(
                getProductDetailResponseType, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle MethodArgumentTypeMismatchException
     */
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {

        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

        GetProductDetailResponseType getProductDetailResponseType = new GetProductDetailResponseType();
        CommonResponseType commonResponseType = new CommonResponseType();
        WarningErrorParamsType warningErrorParamsType = new WarningErrorParamsType();

        WarningErrorType warningErrorType = new WarningErrorType();
        warningErrorType.setCode("666.999.444");
        warningErrorType.setDescription("Handle MethodArgumentTypeMismatchException :: " + error);
        warningErrorType.setType(HttpStatus.BAD_REQUEST.toString());

        warningErrorParamsType.setWarningError(warningErrorType);
        commonResponseType.setErrorsAndWarnings(warningErrorParamsType);
        getProductDetailResponseType.setParams(commonResponseType);

        if (log.isDebugEnabled()) {
            log.debug("Handle MethodArgumentTypeMismatchException :: {}\n---------\n{}\n---------\n", ex.getLocalizedMessage(), printStackTrace(ex.getStackTrace()));
        }

        return new ResponseEntity<Object>(
                getProductDetailResponseType, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle NoHandlerFoundException
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        GetProductDetailResponseType getProductDetailResponseType = new GetProductDetailResponseType();
        CommonResponseType commonResponseType = new CommonResponseType();
        WarningErrorParamsType warningErrorParamsType = new WarningErrorParamsType();

        WarningErrorType warningErrorType = new WarningErrorType();
        warningErrorType.setCode("666.999.555");
        warningErrorType.setDescription("Handle NoHandlerFoundException :: " + error);
        warningErrorType.setType(HttpStatus.NOT_FOUND.toString());

        warningErrorParamsType.setWarningError(warningErrorType);
        commonResponseType.setErrorsAndWarnings(warningErrorParamsType);
        getProductDetailResponseType.setParams(commonResponseType);

        if (log.isDebugEnabled()) {
            log.debug("Handle NoHandlerFoundException :: {}\n---------\n{}\n---------\n", ex.getLocalizedMessage(), printStackTrace(ex.getStackTrace()));
        }

        return new ResponseEntity<Object>(getProductDetailResponseType, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handle HttpRequestMethodNotSupportedException
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(
                " method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        GetProductDetailResponseType getProductDetailResponseType = new GetProductDetailResponseType();
        CommonResponseType commonResponseType = new CommonResponseType();
        WarningErrorParamsType warningErrorParamsType = new WarningErrorParamsType();

        WarningErrorType warningErrorType = new WarningErrorType();
        warningErrorType.setCode("666.999.666");
        warningErrorType.setDescription("Handle HttpRequestMethodNotSupportedException :: " + builder);
        warningErrorType.setType(HttpStatus.METHOD_NOT_ALLOWED.toString());

        warningErrorParamsType.setWarningError(warningErrorType);
        commonResponseType.setErrorsAndWarnings(warningErrorParamsType);
        getProductDetailResponseType.setParams(commonResponseType);

        if (log.isDebugEnabled()) {
            log.debug("Handle HttpRequestMethodNotSupportedException :: {}\n---------\n{}\n---------\n", ex.getLocalizedMessage(), printStackTrace(ex.getStackTrace()));
        }

        return new ResponseEntity<Object>(
                getProductDetailResponseType, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Handle HttpMediaTypeNotSupportedException
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));

        GetProductDetailResponseType getProductDetailResponseType = new GetProductDetailResponseType();
        CommonResponseType commonResponseType = new CommonResponseType();
        WarningErrorParamsType warningErrorParamsType = new WarningErrorParamsType();

        WarningErrorType warningErrorType = new WarningErrorType();
        warningErrorType.setCode("666.999.777");
        warningErrorType.setDescription("Handle HttpMediaTypeNotSupportedException :: " + builder);
        warningErrorType.setType(HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString());

        warningErrorParamsType.setWarningError(warningErrorType);
        commonResponseType.setErrorsAndWarnings(warningErrorParamsType);
        getProductDetailResponseType.setParams(commonResponseType);

        if (log.isDebugEnabled()) {
            log.debug("Handle HttpMediaTypeNotSupportedException :: {}\n---------\n{}\n---------\n", ex.getLocalizedMessage(), printStackTrace(ex.getStackTrace()));
        }

        return new ResponseEntity<Object>(
                getProductDetailResponseType, new HttpHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Default Handler
     */
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

        GetProductDetailResponseType getProductDetailResponseType = new GetProductDetailResponseType();
        CommonResponseType commonResponseType = new CommonResponseType();
        WarningErrorParamsType warningErrorParamsType = new WarningErrorParamsType();

        WarningErrorType warningErrorType = new WarningErrorType();
        warningErrorType.setCode("666.999.888");
        warningErrorType.setDescription("Default Handler :: " + ex.getLocalizedMessage());
        warningErrorType.setType(HttpStatus.INTERNAL_SERVER_ERROR.toString());

        warningErrorParamsType.setWarningError(warningErrorType);
        commonResponseType.setErrorsAndWarnings(warningErrorParamsType);
        getProductDetailResponseType.setParams(commonResponseType);

        if (log.isDebugEnabled()) {
            log.debug("Default Handler :: {}\n---------\n{}\n---------\n", ex.getLocalizedMessage(), printStackTrace(ex.getStackTrace()));
        }

        return new ResponseEntity<Object>(
                getProductDetailResponseType, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String printStackTrace(StackTraceElement[] stackTraceElements) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : stackTraceElements) {
            sb.append(element.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

}
