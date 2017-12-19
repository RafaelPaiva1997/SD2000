package exceptions;

public class PasswordException extends Exception {
    public PasswordException() {
        super("Password inválida para este Username!");
    }
}
