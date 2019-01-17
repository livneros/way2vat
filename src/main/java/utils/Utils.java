package utils;

import com.google.api.server.spi.response.BadRequestException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static utils.Constants.EMPTY_STRING;

public class Utils {

    private static final String EXPECTED_OBJECT_GOT_NULL = "Expected Object, got null";

    static void checkNotNull(Object obj) throws BadRequestException {
        if (obj == null)
            throw new BadRequestException(EXPECTED_OBJECT_GOT_NULL);
    }

    public static <T> T checkNotNull(T obj, String msg) throws BadRequestException {
        if (obj == null)
            throw new BadRequestException(msg);
        return obj;
    }

    public static <T> T checkNotNull(T obj, String template, Object... args) throws BadRequestException {
        String msg = args == null ? template: String.format(template, args);
        return checkNotNull(obj, msg);
    }

    public static void checkNotNull(Object... objects) throws BadRequestException {
        for (Object object : objects) {
            if (object == null)
                throw new BadRequestException(EXPECTED_OBJECT_GOT_NULL);
        }
    }

    public static String convertNullToEmptyString(String content) {
        try{
            checkNotNull(content);
            return content;
        }catch (Exception e){
            return EMPTY_STRING;
        }
    }

    public static long getCurrentMilliseconds() {
        return System.currentTimeMillis();
    }
}
