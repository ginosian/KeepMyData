package demo.exceptions;

/**
 * Created by Test on 9/16/2016.
 */
public class NoSuchRoleException extends RuntimeException {

    public NoSuchRoleException() {
        super("Specified role does not exist");
    }

}
