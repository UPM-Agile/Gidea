package upm.gidea.exceptions;

/**
 *
 * @author user
 */
public class RollbackFailureException extends Exception {

    /**
     *
     * @param message
     * @param cause
     */
    public RollbackFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param message
     */
    public RollbackFailureException(String message) {
        super(message);
    }
}
