package demo.exceptions;

/**
 * Created by Test on 9/16/2016.
 */
public class NoSuchCatalogException extends Exception {

    public NoSuchCatalogException() {
        super("Specified catalog does not exist");
    }

}
