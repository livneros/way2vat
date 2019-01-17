package utils;

public class BadDataException extends RuntimeException {

    public BadDataException() {
        super();
    }

    BadDataException(String s) {
        super(s);
    }

    public BadDataException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public BadDataException(Throwable throwable) {
        super(throwable);
    }
}

