package heap;

import org.junit.Test;


//import java.util.ArrayList;

import static org.junit.Assert.*;

//import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    /* Be sure to write randomized tests that can handle millions of items. To
     * test for runtime, compare the runtime of NaiveMinPQ vs ArrayHeapMinPQ on
     * a large input of millions of items. */
    private static ExtrinsicMinPQ minpq;


    @Test
    public void testSimple() {

        minpq = new ArrayHeapMinPQ();
        minpq.add("3ali", 3);
        minpq.add("4veli", 4);
        minpq.add("2deli", 2);
        minpq.add("5ali", 5);
        minpq.add("1veli", 1);
        minpq.add("6deli", 6);
        minpq.add("2ali", 2);
        minpq.add("8veli", 8);
        minpq.add("10deli", 10);
        // String holder = minpq.getSmallest();
        // minpq.removeSmallest();
        // minpq.removeSmallest();





    }


}
