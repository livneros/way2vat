package utils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class UtilsTest extends Utils {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test(expected = NullPointerException.class)
    public void checkNotNull_simpleNull_shouldThrow() {
        Object object = null;
        checkNotNull(object);
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

}