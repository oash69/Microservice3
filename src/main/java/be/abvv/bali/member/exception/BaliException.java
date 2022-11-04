package be.abvv.bali.member.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Bali Exception.
 */
@Getter
@Setter
public class BaliException extends RuntimeException {
  protected int errorCode;
  protected String exceptionPrefix;
  protected String errorMessage;

  /**
   * Bali Exception Constructor.
   *
   * @param message error message
   */
  public BaliException(String message) {
    super(message);
  }
}
