package autocomplete;

public class Term implements Comparable<Term> {
    private String name;
    private long weight;
    /**
     * Initializes a term with the given query string and weight.
     * @throws IllegalArgumentException if query is null or weight is negative
     */
    public Term(String query, long weight) {
        if (query == null || weight < 0) {
            throw new IllegalArgumentException();
        }
        this.name = query;
        this.weight = weight;
    }

    /**
     * Compares the two terms in lexicographic order by query.
     * @throws NullPointerException if the specified object is null
     */
    public int compareTo(Term that) {
        if (that == null) {
            throw new NullPointerException();
        }
        String thisName = this.name;
        String thatName = that.name;
        int lengthToCompare = Integer.min(thisName.length(), thatName.length());
        int compareResult;
        int i;
        for (i = 0; i < lengthToCompare; i++) {
            compareResult = (int) thisName.charAt(i)-(int) thatName.charAt(i);
            if (compareResult != 0) {
                return compareResult;
            }
        }
        /*
        if (thisName.length() < thatName.length()) {
            return -(int) thatName.charAt(i);
        }
        else if (thisName.length() > thatName.length()) {
            return (int) thisName.charAt(i);
        } */
        return 0;
    }

    /** Compares to another term, in descending order by weight. */
    public int compareToByReverseWeightOrder(Term that) {
        return (int) (that.weight - this.weight); //?
    }

    /**
     * Compares to another term in lexicographic order, but using only the first r characters
     * of each query. If r is greater than the length of any term's query, compares using the
     * term's full query.
     * @throws IllegalArgumentException if r < 0
     */
    public int compareToByPrefixOrder(Term that, int r) {
        if (r < 0) {
            throw new IllegalArgumentException();
        }
        String thisName = this.name;
        String thatName = that.name;
        int lengthToCompare = Integer.min(r, (Integer.min(thisName.length(), thatName.length()))); //correct?
        int compareResult;
        for (int i = 0; i < lengthToCompare; i++) {
            compareResult = (int) thisName.charAt(i)-(int) thatName.charAt(i);
            if (compareResult != 0) {
                return compareResult;
            }
        }
        return 0;
    }

    /** Returns this term's query. */
    public String query() {
        return this.name;
    }

    /*
    @Override
    public String toString() {
        return "Term{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }
     */

    /**
     * Returns the first r characters of this query.
     * If r is greater than the length of the query, returns the entire query.
     * @throws IllegalArgumentException if r < 0
     */
    public String queryPrefix(int r) {
        if (r < 0) {
            throw new IllegalArgumentException();
        }
        if (r > name.length()) {
            return name;
        }
        char[] nameC = name.toCharArray();
        String namePortion = new String(nameC, 0, r);
        return namePortion;
    }

    /** Returns this term's weight. */
    public long weight() {
        return this.weight;
    }
}
