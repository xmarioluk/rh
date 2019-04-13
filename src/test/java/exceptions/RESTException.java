package exceptions;

public class RESTException extends RuntimeException {
    public RESTException(String message) {
        super(message);
    }
    public RESTException(Exception e) { super(e); }
}
