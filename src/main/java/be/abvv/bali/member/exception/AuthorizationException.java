package be.abvv.bali.member.exception;

/**
 * Authorization Exception.
 */
public class AuthorizationException extends BaliException {
  public static final String INVALID_AUTH_EX = "AEX";

  /**
   * Authorization Exception constructor.
   *
   * @param message error message
   * @param errorCode error code
   */
  public AuthorizationException(String message, int errorCode) {
    super(message);
    this.errorCode = errorCode;
    this.exceptionPrefix = AuthorizationException.INVALID_AUTH_EX;
  }
}
