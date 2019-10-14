package deques;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class ArrayDequeTest {

    @Test
    public void testTricky() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(0);
        assertEquals(0, (int) deque.get(0));

        deque.addLast(1);
        assertEquals(1, (int) deque.get(1));

        deque.addFirst(-1);
        deque.addLast(2);
        assertEquals(2, (int) deque.get(3));

        deque.addLast(3);
        deque.addLast(4);

        // Test that removing and adding back is okay
        assertEquals(-1, (int) deque.removeFirst());
        deque.addFirst(-1);
        assertEquals(-1, (int) deque.get(0));

        deque.addLast(5);
        deque.addFirst(-2);
        deque.addFirst(-3);

        // Test a tricky sequence of removes
        assertEquals(-3, (int) deque.removeFirst());
        assertEquals(5, (int) deque.removeLast());
        assertEquals(4, (int) deque.removeLast());
        assertEquals(3, (int) deque.removeLast());
        assertEquals(2, (int) deque.removeLast());
        // Failing test
        assertEquals(1, (int) deque.removeLast());
    }

    @Test
    public void aBunch() {
        ArrayDeque<String> lld = new ArrayDeque<>();

        assertTrue(lld.isEmpty());

        lld.addFirst("3");
        assertEquals(1, lld.size());
        assertFalse(lld.isEmpty());

        lld.addLast("4");
        assertEquals(2, lld.size());

        assertEquals("3", lld.removeFirst());
        assertEquals(1, lld.size());

        lld.addFirst("3");
        assertEquals(2, lld.size());

        lld.addFirst("2");
        assertEquals(3, lld.size());

        lld.addFirst("1");
        assertEquals(4, lld.size());

        lld.addLast("5");
        assertEquals(5, lld.size());

        assertEquals("1", lld.get(0));
        assertEquals("2", lld.get(1));
        assertEquals("3", lld.get(2));
        assertEquals("4", lld.get(3));
        assertEquals("5", lld.get(4));
        assertNull(lld.get(5));
        assertNull(lld.get(-1));

        assertEquals("5", lld.removeLast());
        assertEquals(4, lld.size());

        assertEquals("4", lld.removeLast());
        assertEquals(3, lld.size());

        assertEquals("1", lld.removeFirst());
        assertEquals(2, lld.size());
    }
}
