package be.abvv.bali.member.exception;


/**
 * ApiExceptionModel
 */
public class ApiExceptionModel extends Exception{

  private String code;
  private String message;
  private String moreInformation;
  private String uuid;

  public ApiExceptionModel code(String code) {
    this.code = code;
    return this;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMoreInformation() {
    return moreInformation;
  }

  public void setMoreInformation(String moreInformation) {
    this.moreInformation = moreInformation;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
}

