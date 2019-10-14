package autocomplete;

import edu.princeton.cs.algs4.In;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryRangeSearchTest {

    private static Autocomplete lAuto;
    private static Autocomplete bAuto;

    private static int N = 0;
    private static Term[] terms = null;

    private static final String INPUT_FILENAME = "data/cities.txt";

    /**
     * Creates LinearRangeSearch and BinaryRangeSearch instances based on the data from cities.txt
     * so that they can easily be used in tests.
     */
    @Before
    public void setUp() {
        if (terms != null) {
            return;
        }

        In in = new In(INPUT_FILENAME);
        N = in.readInt();
        terms = new Term[N];
        for (int i = 0; i < N; i += 1) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query, weight);
        }

        lAuto = new LinearRangeSearch(terms);
        bAuto = new BinaryRangeSearch(terms);
    }

    /**
     * Checks that the terms in the expected array are equivalent to the ones in the actual array.
     */
    private void assertTermsEqual(Term[] expected, Term[] actual) {
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            Term e = expected[i];
            Term a = actual[i];
            assertEquals(e.query(), a.query());
            assertEquals(e.weight(), a.weight());
        }
    }

    @Test
    public void testRandomized() {
        assertTermsEqual(bAuto.allMatches("Dal"), lAuto.allMatches("Dal"));

        assertTermsEqual(bAuto.allMatches("İst"), lAuto.allMatches("İst"));
        assertTermsEqual(bAuto.allMatches("Cann"), lAuto.allMatches("Cann"));

        assertTermsEqual(bAuto.allMatches("au"), lAuto.allMatches("au"));
        assertTermsEqual(bAuto.allMatches(" "), lAuto.allMatches(" "));
        assertTermsEqual(bAuto.allMatches("tt"), lAuto.allMatches("tt"));
        //  assertTermsEqual(binaryAuto.allMatches("Be"), linearAuto.allMatches("Be"));

        // assertTermsEqual(binaryAuto.allMatches("Da"), linearAuto.allMatches("Da"));
        assertTermsEqual(bAuto.allMatches("Dallas"), lAuto.allMatches("Dallas"));
        assertTermsEqual(bAuto.allMatches("Sqx"), lAuto.allMatches("Sqx"));
        assertTermsEqual(bAuto.allMatches("Pari"), lAuto.allMatches("Pari"));

        assertTermsEqual(bAuto.allMatches("Kalamanaamanakananamajja"), lAuto.allMatches("Kalamanaamanakananamajja"));
        assertTermsEqual(bAuto.allMatches("Пл"), lAuto.allMatches("Пл"));
        assertTermsEqual(bAuto.allMatches("东海岛"), lAuto.allMatches("东海岛"));
        assertTermsEqual(bAuto.allMatches("Berlin, "), lAuto.allMatches("Berlin, "));
        assertTermsEqual(bAuto.allMatches("Z"), lAuto.allMatches("Z"));
        assertTermsEqual(bAuto.allMatches("'s"), lAuto.allMatches("'s"));


    }

    @Test
    public void testSimpleExampleLinear() {
        Term[] moreTerms = new Term[] {
                new Term("hello", 0),
                new Term("world", 0),
                new Term("welcome", 0),
                new Term("to", 0),
                new Term("autocomplet", 0),
                new Term("me", 0)
        };
        LinearRangeSearch lrs = new LinearRangeSearch(moreTerms);
        Term[] expected = new Term[]{new Term("autocomplet", 0)};
        assertTermsEqual(expected, lrs.allMatches("auto"));
    }

    @Test
    public void testSimpleExampleLinear2() {
        Term[] moreTerms = new Term[] {
                new Term("autobahn", 5),
                new Term("world", 0),
                new Term("welcome", 0),
                new Term("automatic", 2),
                new Term("autocomplete", 19),
                new Term("me", 0)
        };
        LinearRangeSearch lrs = new LinearRangeSearch(moreTerms);
        BinaryRangeSearch brs = new BinaryRangeSearch(moreTerms);
        Term[] expected = new Term[]{new Term("autocomplete", 19), new Term("autobahn", 5), new Term("automatic", 2)};
        assertTermsEqual(expected, lrs.allMatches("auto"));
        assertTermsEqual(expected, brs.allMatches("auto"));
    }

    @Test
    public void testSimpleCompared() {
        Term[] moreTerms = new Term[] {
                new Term("autobahn", 5),
                new Term("world", 0),
                new Term("welcome", 0),
                new Term("automatic", 2),
                new Term("autocomplete", 19),
                new Term("me", 0)
        };
        LinearRangeSearch lrs = new LinearRangeSearch(moreTerms);
        BinaryRangeSearch brs = new BinaryRangeSearch(moreTerms);
        // Term[] expected = new Term[]{new Term("autocomplete", 19), new Term("autobahn", 5),new Term("automatic", 2)};
        assertTermsEqual(brs.allMatches("au"), lrs.allMatches("au"));
    }

    @Test
    public void testSimpleCompared2() {
        Term[] moreTerms = new Term[] {
                new Term("autobahn", 5),
                new Term("world", 0),
                new Term("welcome", 0),
                new Term("automatic", 2),
                new Term("autocomp", 19),
                new Term("me", 0),
                new Term(" ", 0),
                new Term("auu", 12)
        };
        LinearRangeSearch lrs = new LinearRangeSearch(moreTerms);
        BinaryRangeSearch brs = new BinaryRangeSearch(moreTerms);
        Term[] expected = new Term[]{new Term("autocomp", 19), new Term("autobahn", 5), new Term("automatic", 2)};
        assertTermsEqual(expected, lrs.allMatches("aut"));
        assertTermsEqual(brs.allMatches("aut"), lrs.allMatches("aut"));
    }

    @Test
    public void testSimpleCompared3() {
        Term[] moreTerms = new Term[] {
                new Term("autobahn", 5),
                new Term("world", 0),
                new Term("welcome", 0),
                new Term("automatic", 2),
                new Term("autocomplete", 19),
                new Term("me", 0),
                new Term(" ", 0),
                new Term("auu", 12)
        };
        LinearRangeSearch lrs = new LinearRangeSearch(moreTerms);
        BinaryRangeSearch brs = new BinaryRangeSearch(moreTerms);
        assertTermsEqual(brs.allMatches(" "), lrs.allMatches(" "));
    }


    @Test
    public void testSimpleExample() {
        Term[] moreTerms = new Term[] {
            new Term("hello", 0),
            new Term("world", 0),
            new Term("welcome", 0),
            new Term("to", 0),
            new Term("autocomplete", 0),
            new Term("me", 0)
        };
        BinaryRangeSearch brs = new BinaryRangeSearch(moreTerms);
        Term[] expected = new Term[]{new Term("autocomplete", 0)};
        assertTermsEqual(expected, brs.allMatches("auto"));
    }

    // Write more unit tests below.
}
