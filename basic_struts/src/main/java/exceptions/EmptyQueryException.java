package exceptions;

public class EmptyQueryException extends Exception {
    public EmptyQueryException() {
    }

    public EmptyQueryException(String message) {
        super(message);
    }
}
