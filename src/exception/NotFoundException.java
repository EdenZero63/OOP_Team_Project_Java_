package exception;

public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}
public class AlreadyExistsException extends Exception {
    // Where the NotFoundException is thrown when it should exist but doesn't, the AlreadyExistsException is thrown when it shouldn't exist but does.
    public AlreadyExistsException(String message) {
        super(message);
    }
}
