package utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import static utils.Utils.checkNotNull;

public class UtilsTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test(expected = NullPointerException.class)
    public void checkNotNull_simpleNull_shouldThrow() {
        checkNotNull((Object) null);
    }

    @Test
    public void checkNotNull_emptyObject_shouldNOTThrow() {
        Object object = new Object();
        checkNotNull(object);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void checkNotNull_WithMsg_simpleNull_shouldThrow() {
        Object object = null;
        String errorMsg = "ERROR";
        expectedEx.expect(NullPointerException.class);
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