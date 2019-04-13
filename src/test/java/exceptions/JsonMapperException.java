package exceptions;

public class JsonMapperException extends RuntimeException {
    public JsonMapperException(Exception e) { super(e); }
}
