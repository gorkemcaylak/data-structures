package deques.palindrome;
import org.junit.Test;

import static org.junit.Assert.*;

public class OffByOneTest {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testOff() {
        assertTrue(offByOne.equalChars('k', 'l'));
        assertFalse(offByOne.equalChars('k', 'm'));
        assertFalse(offByOne.equalChars('k', 'k'));
        assertFalse(offByOne.equalChars('K', 'K'));
        assertFalse(offByOne.equalChars('k', 'L'));
        assertTrue(offByOne.equalChars('l', 'k'));
        assertFalse(offByOne.equalChars('L', 'k'));
        assertFalse(offByOne.equalChars('k', 'm'));
        assertTrue(offByOne.equalChars('K', 'L'));
        assertTrue(offByOne.equalChars('K', 'J'));
        assertFalse(offByOne.equalChars('K', 'M'));
        assertTrue(offByOne.equalChars('Z', '['));
        assertTrue(offByOne.equalChars('/', '0'));
    }
}
