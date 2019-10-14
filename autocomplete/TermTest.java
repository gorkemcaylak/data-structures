package autocomplete;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TermTest {
    @Test
    public void testSimpleCompareTo() {
        Term a = new Term("autocomplete", 0);
        Term b = new Term("me", 0);
        assertTrue(a.compareTo(b) < 0); // "autocomplete" < "me"
    }

    @Test
    public void testFundCompareTo() {
        Term a = new Term("autocomplete", 0);
        Term b = new Term("me", 0);
        assertTrue(a.compareTo(b)==-b.compareTo(a)); // "autocomplete" < "me"

        a = new Term("autocomplete", 0);
        b = new Term("auto", 0);
        assertTrue(a.compareTo(b)==-b.compareTo(a));

    }

    @Test
    public void testFundCompareByPrefix() {
        Term a = new Term("autocomplete", 0);
        Term b = new Term("aut", 0);
        assertTrue(a.compareToByPrefixOrder(b, 3)==-b.compareToByPrefixOrder(a, 3)); // "autocomplete" < "me"
        assertTrue(a.compareToByPrefixOrder(b, 2)==-b.compareToByPrefixOrder(a, 2)); // "autocomplete" < "me"
        assertTrue(a.compareToByPrefixOrder(b, 4)==-b.compareToByPrefixOrder(a, 4)); // "autocomplete" < "me"

        a = new Term("autocomplete", 0);
        b = new Term("auto", 0);
        assertTrue(a.compareTo(b)==-b.compareTo(a));

    }

    // Write more unit tests below.
}
