package utils;

import com.google.api.server.spi.response.BadRequestException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import static utils.Utils.checkNotNull;

public class UtilsTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @SuppressWarnings("ConstantConditions")
    @Test
    public void checkNotNull_simpleNull_shouldThrow() throws BadRequestException {
        expectedEx.expect(BadRequestException.class);
        checkNotNull((Object) null);
    }

    @Test
    public void checkNotNull_emptyObject_shouldNOTThrow() throws BadRequestException {
        Object object = new Object();
        checkNotNull(object);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void checkNotNull_WithMsg_simpleNull_shouldThrow() throws BadRequestException {
        Object object = null;
        String errorMsg = "ERROR";
        expectedEx.expect(BadRequestException.class);
        expectedEx.expectMessage(errorMsg);
        checkNotNull(object, errorMsg);
    }

    @Test
    public void getCurrentMilliseconds_SanityCheck() {
        long cur = Utils.getCurrentMilliseconds();
        long dateOnTestCreationDate = 1547717045000L;
        assertTrue(cur > dateOnTestCreationDate);
    }
}