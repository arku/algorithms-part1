import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by zarak on 2016-09-15.
 */
public class DequeTest {

    private Deque<String> deque;
    @Before
    public void setup() {
         deque = new Deque<String>();
    }

    @Test
    public void isEmptyTest() {
        assertEquals(true, deque.isEmpty());
    }

    @Test(expected=NullPointerException.class)
    public void addFirstExceptionTest() {
        deque.addFirst(null);
    }

    @Test
    public void addFirstTest() {
        assertNull(deque.getFirst());
        assertNull(deque.getLast());
        deque.addFirst("hi");

        assertNotNull(deque.getFirst());
        assertEquals(deque.getFirst().getItem(), deque.getLast().getItem());

        assertNull(deque.getFirst().getPrev());
        assertNull(deque.getFirst().getNext());
        assertNull(deque.getLast().getNext());
        assertNull(deque.getLast().getPrev());

        deque.addFirst("bye");
        assertEquals(deque.getFirst().getItem(), "bye");
        assertEquals(deque.getLast().getItem(), "hi");

        assertNull(deque.getFirst().getPrev());
        assertEquals(deque.getFirst().getNext().getPrev().getItem(), "bye");


        assertEquals(deque.getFirst().getNext().getItem(), "hi");
        assertNull(deque.getLast().getNext());
    }

    @Test(expected=NullPointerException.class)
    public void addLastExceptionTest() {
        deque.addLast(null);
    }

    @Test
    public void addLastTest() {
        deque.addLast("hi");

        assertEquals(deque.getLast().getItem(), "hi");
        assertEquals(deque.getFirst().getItem(), deque.getLast().getItem());
        assertNull(deque.getLast().getPrev());

        deque.addLast("hello");
        deque.addLast("goodbye");

        assertEquals(deque.getFirst().getItem(), "hi");
        assertEquals(deque.getLast().getItem(), "goodbye");
        assertNotNull(deque.getLast().getPrev());

        assertEquals(deque.getLast().getPrev().getItem(), "hello");
        assertEquals(deque.getFirst().getNext().getNext().getItem(), "goodbye");
        assertNull(deque.getLast().getNext());
        assertEquals(deque.size(), 3);
    }

    @Test (expected = NoSuchElementException.class)
    public void removeFirstExceptionTest() {
        deque.removeFirst();
    }

    @Test
    public void removeFirstTest() {
        deque.addLast("hi");
        deque.addFirst("hello");
        assertEquals(deque.size(), 2);

        String removed = deque.removeFirst();
        assertEquals(removed, "hello");
        assertEquals(deque.getFirst().getItem(), "hi");
        assertEquals(deque.getLast().getItem(), "hi");
        assertEquals(deque.size(), 1);
        assertNull(deque.getFirst().getNext());
        assertNull(deque.getFirst().getPrev());
        assertNull(deque.getLast().getNext());
        assertNull(deque.getLast().getPrev());


        removed = deque.removeFirst();
        assertEquals(removed, "hi");
        assertNull(deque.getFirst());
        assertNull(deque.getLast());
        assertEquals(deque.size(), 0);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeLastExceptionTest() {
        deque.removeLast();
    }

    @Test
    public void removeLastTest() {
        deque.addFirst("hi");
        deque.addLast("bye");

        String removed = deque.removeLast();
        assertEquals(removed, "bye");
        assertEquals(deque.getLast().getItem(), "hi");

        assertNull(deque.getLast().getPrev());
        assertNull(deque.getLast().getNext());
        assertNull(deque.getFirst().getPrev());
        assertNull(deque.getFirst().getNext());

        removed = deque.removeLast();
        assertEquals(removed, "hi");
        assertEquals(true, deque.isEmpty());
        assertNull(deque.getLast());
        assertNull(deque.getFirst());


    }

}