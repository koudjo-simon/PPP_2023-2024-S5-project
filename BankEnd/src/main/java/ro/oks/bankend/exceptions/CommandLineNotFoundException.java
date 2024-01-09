package ro.oks.bankend.exceptions;

public class CommandLineNotFoundException extends Exception {
    public CommandLineNotFoundException(String message) {
        super(message);
    }
}
