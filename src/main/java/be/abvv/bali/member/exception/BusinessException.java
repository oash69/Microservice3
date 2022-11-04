package be.abvv.bali.member.exception;

/**
 * Business Exception.
 */
public class BusinessException extends BaliException {
  public static final String INVALID_BUSINESS_EX = "BUE";
  public static final Integer EMPLOYEE_NOT_FOUND  = -1;
  public static final Integer C32MEMBER_NOT_FOUND = -2;
  public static final Integer MEMBER_NOT_FOUND = -3;
  public static final Integer NISS_NOT_VALID = -4;
  public static final Integer C32MEMBER_NOT_INTEGRATED = -5;
  public static final Integer NO_RESULTS_SEARCH_BY_NAME = -6;

  /**
   * Business Exception.
   *
   * @param message error message
   * @param errorCode error code
   */
  public BusinessException(String message, int errorCode) {
    super(message);
    this.errorCode = errorCode;
    this.exceptionPrefix = INVALID_BUSINESS_EX;
  }
}
