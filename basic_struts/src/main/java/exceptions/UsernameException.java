package exceptions;

public class UsernameException extends Exception {
    public UsernameException() {
        super("Username não encontrado!");
    }
}
