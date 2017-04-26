package com.product.exception;

import com.product.exception.dto.Error;
import com.product.exception.dto.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by associate on 4/20/17.
 */
@ControllerAdvice
public class AppExceptionHandler {

    Locale currentLocale = LocaleContextHolder.getLocale();

    @Autowired
    MessageSource messageSource;


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse processError(Exception ex) {
        Error error = new Error("9999", ex.getMessage());
        return new ErrorResponse(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        return processFieldError(result.getAllErrors());
    }

    private ErrorResponse processFieldError(List<ObjectError> errors)
    {
        ErrorResponse errorResponse = new ErrorResponse();
        ArrayList<Error> errorList = new ArrayList<>();

        StringBuffer errorMsg = new StringBuffer();
        if (!CollectionUtils.isEmpty(errors))
        {
            errors.stream().forEach(error -> errorList.add(getErrorMessage(error.getCode())));
            errorResponse.setErrors(errorList);
        }
        return errorResponse;
    }

    /**
     * Prepare error response with list of errors
     *
     * Read the error code and message from the message.properties file using the validation / javax contraint messages
     * These constraint messages should be a property / key name in the message.properties file.
     * @param errorCode
     * @return
     */
    public Error getErrorMessage(String errorCode) {
        Error errorObject = null;

        String errorValue = messageSource.getMessage(errorCode, null, currentLocale);

        if (!StringUtils.isEmpty(errorValue)) {
            errorObject = new Error("PRODUCT_MS_" + errorCode, errorValue);
        }
        else{
            errorObject = new Error("PRODUCT_MS_4xx","Invalid request parameters - " + errorCode);
        }

        return errorObject;
    }
}
