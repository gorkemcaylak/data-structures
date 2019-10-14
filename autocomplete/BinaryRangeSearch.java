package autocomplete;

import java.util.Arrays;

public class BinaryRangeSearch implements Autocomplete {
    private int length;
    private static Term[] sortedTerms;
    private static TermComparators termComp;

    private static int bsHelper(Term tested, int start, int end){
        if (start > end ) { //|| end < 1) {//?????
            return -1;
        }
        int mid = (start + end) / 2;  // start=end=0
        int testResult = tested.compareTo(sortedTerms[mid]);
        if (testResult == 0) {
            return mid;
        }
        else if (testResult < 0) {
            return bsHelper(tested, start, mid-1);
        }
        else {
            return bsHelper(tested, mid+1, end);
        }


    }


    /**
     * Validates and stores the given array of terms.
     * Assumes that the given array will not be used externally afterwards (and thus may directly
     * store and mutate it).
     * @throws IllegalArgumentException if terms is null or contains null
     */
    public BinaryRangeSearch(Term[] terms) {
        if (terms == null) {
            throw new IllegalArgumentException();
        }
        else if (Arrays.asList(terms).contains(null)) {
            throw new IllegalArgumentException();
        }
        Arrays.sort(terms, Term::compareTo);
        this.sortedTerms = terms;
        this.length = terms.length;
        //throw new UnsupportedOperationException("Not implemented yet: replace this with your code.");
    }

    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * @throws IllegalArgumentException if prefix is null
     */
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException();
        }
        Term tested = new Term(prefix, prefix.length());
        int temp = bsHelper(tested, 0, length-1);
        int left = -1;
        int right = -1;
        while (temp != -1) {
            left = temp;
            temp = bsHelper(tested, 0, temp-1);
        }
        temp = bsHelper(tested, 0, length-1);
        while (temp != -1) {
            right = temp;
            temp = bsHelper(tested, temp+1, length-1);
        }
        if (left != -1 && right != -1) {
            Term[] out = new Term[right-left + 1];
            for (int i = left; i < right+1; i++) {
                out[i-left] = sortedTerms[i];
            }
            Arrays.sort(out, termComp.byReverseWeightOrder());
            return out;
        }
        Term[] empty = new Term[0];
        return empty;
        //return null?
    }
}
