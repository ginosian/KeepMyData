package demo.exceptions;

/**
 * Created by Test on 9/16/2016.
 */
public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException() {
        super("Specified user does not exist");
    }
}
