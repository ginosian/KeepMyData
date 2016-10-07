package demo.exceptions;

/**
 * Created by Test on 9/16/2016.
 */
public class EmptyRequiredValueException extends RuntimeException {

    public EmptyRequiredValueException() {
        super("Passed value is empty.");
    }
}
