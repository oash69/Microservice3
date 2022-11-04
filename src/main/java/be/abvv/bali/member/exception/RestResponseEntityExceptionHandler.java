package be.abvv.bali.member.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Rest Response Entity Exception Handler.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(value = {InvalidInputException.class })
  protected ResponseEntity<Object> handleBadRequest(final InvalidInputException ex,
                                                    final WebRequest request) {
    //String bodyOfResponse = String.format("Code: %s""This should be application specific";

    ExceptionAPIResponse exResponse = new ExceptionAPIResponse();
    exResponse.setHttpCode(String.valueOf(HttpStatus.BAD_REQUEST));
    exResponse.setHttpMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
    exResponse.setMoreInformation("Invalid parameter");
    exResponse.setRequestId(UUID.randomUUID().toString());

    ApiExceptionModel modelApiEx = new ApiExceptionModel();
    modelApiEx.setCode(String.format("DDS-%s-%03d",
        ex.getExceptionPrefix(),
        Math.abs(ex.getErrorCode())));
    modelApiEx.setMessage("InvalidInputException");
    modelApiEx.setMoreInformation(ex.getMessage());
    modelApiEx.setUuid(UUID.randomUUID().toString());

    List<ApiExceptionModel> exceptionList = new ArrayList<>();
    exceptionList.add(modelApiEx);

    exResponse.setErrors(exceptionList);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);

    return handleExceptionInternal(ex,
                                   exResponse,
                                   httpHeaders,
                                   HttpStatus.BAD_REQUEST,
                                   request);
  }

  @ExceptionHandler(value = { AuthorizationException.class })
  protected ResponseEntity<Object> handleBadRequest(final AuthorizationException ex,
                                                    final WebRequest request) {
    ExceptionAPIResponse exResponse = new ExceptionAPIResponse();
    exResponse.setHttpCode(String.valueOf(HttpStatus.UNAUTHORIZED));
    exResponse.setHttpMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());
    exResponse.setMoreInformation("Not authorized");
    exResponse.setRequestId(UUID.randomUUID().toString());

    ApiExceptionModel modelApiEx = new ApiExceptionModel();
    modelApiEx.setCode(String.format("DDS-%s-%03d",
        ex.getExceptionPrefix(),
        Math.abs(ex.getErrorCode())));
    modelApiEx.setMessage("AuthorizationException");
    modelApiEx.setMoreInformation(ex.getMessage());
    modelApiEx.setUuid(UUID.randomUUID().toString());

    List<ApiExceptionModel> exceptionList = new ArrayList<>();
    exceptionList.add(modelApiEx);

    exResponse.setErrors(exceptionList);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);

    return handleExceptionInternal(ex,
        exResponse,
        httpHeaders,
        HttpStatus.UNAUTHORIZED,
        request);
  }

  @ExceptionHandler(value = { BusinessException.class })
  protected ResponseEntity<Object> handleBadRequest(final BusinessException ex,
                                                    final WebRequest request) {
    ExceptionAPIResponse exResponse = new ExceptionAPIResponse();
    exResponse.setHttpCode(String.valueOf(HttpStatus.BAD_REQUEST));
    exResponse.setHttpMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
    exResponse.setMoreInformation("Business Error");
    exResponse.setRequestId(UUID.randomUUID().toString());

    ApiExceptionModel modelApiEx = new ApiExceptionModel();
    modelApiEx.setCode(String.format("DDS-%s-%03d",
        ex.getExceptionPrefix(),
        Math.abs(ex.getErrorCode())));
    modelApiEx.setMessage("BusinessException");
    modelApiEx.setMoreInformation(ex.getMessage());
    modelApiEx.setUuid(UUID.randomUUID().toString());

    List<ApiExceptionModel> exceptionList = new ArrayList<>();
    exceptionList.add(modelApiEx);

    exResponse.setErrors(exceptionList);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);

    return handleExceptionInternal(ex,
        exResponse,
        httpHeaders,
        HttpStatus.BAD_REQUEST,
        request);
  }

  @ExceptionHandler(value = { SystemException.class })
  protected ResponseEntity<Object> handleBadRequest(final SystemException ex,
                                                    final WebRequest request) {
    ExceptionAPIResponse exResponse = new ExceptionAPIResponse();
    exResponse.setHttpCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
    exResponse.setHttpMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    exResponse.setMoreInformation("System Error");
    exResponse.setRequestId(UUID.randomUUID().toString());

    ApiExceptionModel modelApiEx = new ApiExceptionModel();
    modelApiEx.setCode(String.format("DDS-%s-%03d",
        ex.getExceptionPrefix(),
        Math.abs(ex.getErrorCode())));
    modelApiEx.setMessage("SystemException");
    modelApiEx.setMoreInformation(ex.getMessage());
    modelApiEx.setUuid(UUID.randomUUID().toString());

    List<ApiExceptionModel> exceptionList = new ArrayList<>();
    exceptionList.add(modelApiEx);

    exResponse.setErrors(exceptionList);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);

    return handleExceptionInternal(ex,
        exResponse,
        httpHeaders,
        HttpStatus.INTERNAL_SERVER_ERROR,
        request);
  }
}
