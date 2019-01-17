package utils;

public class Utils {

    private static final String EXPECTED_OBJECT_GOT_NULL = "Expected Object, got null";

    static void checkNotNull(Object obj) {
        if (obj == null)
            throw new NullPointerException(EXPECTED_OBJECT_GOT_NULL);
    }

    public static void checkNotEmptyString(String string){
        if(string.equals("")){
            throw new BadDataException(Constants.EXPECTED_NOT_EMPTY_STRING);
        }
    }

    public static <T> T checkNotNull(T obj, String msg) {
        if (obj == null)
            throw new NullPointerException(msg);
        return obj;
    }

    public static <T> T checkNotNull(T obj, String template, Object... args) {
        String msg = args == null ? template: String.format(template, args);
        return checkNotNull(obj, msg);
    }

    public static void checkNotNull(Object... objects) {
        for (Object object : objects) {
            if (object == null)
                throw new NullPointerException(EXPECTED_OBJECT_GOT_NULL);
        }
    }
}
