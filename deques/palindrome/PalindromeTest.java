package deques.palindrome;

import deques.Deque;
import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromeTest {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void simplePalindromeTest() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("!"));
        assertFalse(palindrome.isPalindrome("ab"));
        assertTrue(palindrome.isPalindrome("aa"));
        assertFalse(palindrome.isPalindrome("abc"));
        assertTrue(palindrome.isPalindrome("aba"));
        assertTrue(palindrome.isPalindrome("daad"));
        assertFalse(palindrome.isPalindrome("dead"));
        assertFalse(palindrome.isPalindrome("baad"));
        assertTrue(palindrome.isPalindrome("abcba"));
        assertFalse(palindrome.isPalindrome("abcbb"));
        assertFalse(palindrome.isPalindrome("abcab"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("/racecar/"));
        assertTrue(palindrome.isPalindrome("raceecar"));
        assertFalse(palindrome.isPalindrome("racecaz"));
    }
    @Test
    public void complexPalindromeTest() {
        assertTrue(palindrome.isPalindrome("axb", offByOne));
        assertTrue(palindrome.isPalindrome("x", offByOne));
        assertTrue(palindrome.isPalindrome("axyb", offByOne));
        assertTrue(palindrome.isPalindrome("axcyb", offByOne));
        assertTrue(palindrome.isPalindrome("dcmde", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));

        assertFalse(palindrome.isPalindrome("axc", offByOne));
        assertFalse(palindrome.isPalindrome("axxb", offByOne));
        assertFalse(palindrome.isPalindrome("axdb", offByOne));
        assertFalse(palindrome.isPalindrome("cmne", offByOne));
        assertFalse(palindrome.isPalindrome("eg", offByOne));
        assertFalse(palindrome.isPalindrome("dcmdf", offByOne));

    }
}
