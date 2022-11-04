package be.abvv.bali.member.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Inbox Service Exception.
 */
@Getter
@Setter
public class InvalidInputException extends BaliException {
  public static final String  INVALID_INPUT_EX = "IIE";

  public static final Integer INVALID_SSN                 = -1;
  public static final Integer INVALID_REGION              = -2;

  /**
   * Invalid Input Exception constructor.
   *
   * @param message error message
   * @param errorCode error code
   */
  public InvalidInputException(String message, int errorCode) {
    super(message);
    this.errorMessage = message;
    this.errorCode = errorCode;
    this.exceptionPrefix = InvalidInputException.INVALID_INPUT_EX;
  }
}
