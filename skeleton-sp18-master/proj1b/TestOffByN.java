import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Comparator;

public class TestOffByN {
    static CharacterComparator offBy5 = new OffByN(5);

    @Test
    public void testOffByN() {
        assertTrue(offBy5.equalChars('a','f'));
        assertFalse(offBy5.equalChars('f','h'));
    }
}
