package autocomplete;

import java.util.Arrays;

public class LinearRangeSearch implements Autocomplete {
    private int length;
    private static Term[] terms;
    private static TermComparators termComp;

    /**
     * Validates and stores the given array of terms.
     * Assumes that the given array will not be used externally afterwards (and thus may directly
     * store and mutate it).
     * @throws IllegalArgumentException if terms is null or contains null
     */
    public LinearRangeSearch(Term[] terms) {
        if (terms == null) {
            throw new IllegalArgumentException();
        }
        else if (Arrays.asList(terms).contains(null)) {
            throw new IllegalArgumentException();
        }
        this.terms = terms;
        this.length = terms.length;
    }

    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * @throws IllegalArgumentException if prefix is null
     */
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException();
        }
        Term[] temp = new Term[length];
        Term compared = new Term(prefix, prefix.length());
        int compareResult;
        int index = 0;
        for (Term term : terms) {
            compareResult = term.compareToByPrefixOrder(compared, prefix.length());
            if (compareResult == 0) {
                temp[index] = term;
                index++;
            }
        }
        if (index == 0) {
            Term[] empty = new Term[0];
            return empty;
        }
        Term[] sorted = Arrays.copyOfRange(temp, 0, index);
        Arrays.sort(sorted, termComp.byReverseWeightOrder());
        return sorted;
    }
}

