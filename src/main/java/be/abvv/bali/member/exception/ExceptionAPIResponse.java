package be.abvv.bali.member.exception;

import java.util.List;

/**
 * ExceptionAPIResponse
 */
public class ExceptionAPIResponse {

  private String httpCode;
  private String httpMessage;
  private String moreInformation;
  private String requestId;
  private List<ApiExceptionModel> errors = null;

  public ExceptionAPIResponse httpCode(String httpCode) {
    this.httpCode = httpCode;
    return this;
  }

  public String getHttpCode() {
    return httpCode;
  }

  public void setHttpCode(String httpCode) {
    this.httpCode = httpCode;
  }

  public String getHttpMessage() {
    return httpMessage;
  }

  public void setHttpMessage(String httpMessage) {
    this.httpMessage = httpMessage;
  }

  public String getMoreInformation() {
    return moreInformation;
  }

  public void setMoreInformation(String moreInformation) {
    this.moreInformation = moreInformation;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public List<ApiExceptionModel> getErrors() {
    return errors;
  }

  public void setErrors(List<ApiExceptionModel> errors) {
    this.errors = errors;
  }
}

