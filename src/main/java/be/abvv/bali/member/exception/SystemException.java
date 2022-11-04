package be.abvv.bali.member.exception;

/**
 * System Exception.
 */
public class SystemException extends BaliException {
  public static final String INVALID_SYSTEM_EX = "SSE";

  /**
   * System Exception constructor.
   *
   * @param message error message
   * @param errorCode error code
   */
  public SystemException(String message, int errorCode) {
    super(message);
    this.errorCode = errorCode;
    this.exceptionPrefix = INVALID_SYSTEM_EX;
  }
}
