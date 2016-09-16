import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by lazyass on 9/16/16.
 */
public class RandomizedQueueTest {
    RandomizedQueue<String> queue = new RandomizedQueue<>();

    @Test(expected = NullPointerException.class)
    public void enqueueExceptionTest() {
        queue.enqueue(null);
    }

    @Test
    public void enqueueTest(){
        queue.enqueue("a");

        assertEquals(1, queue.getLength());
        assertEquals(1, queue.size());
        assertEquals("a", queue.getItem(0));

        queue.enqueue("b");
        assertEquals(2, queue.getLength());

        queue.enqueue("c");
        assertEquals(4, queue.getLength());
    }

    @Test(expected = NoSuchElementException.class)
    public void dequeueExceptionTest() {
        queue.dequeue();
    }

    @Test
    public void dequeueTest() {
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");

        assertEquals(5, queue.size());
        String removed = queue.dequeue();
        assertNotNull(removed);
        assertEquals(4, queue.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void sampleExceptionTest() {
        queue.sample();
    }

    @Test
    public void sampleTest() {
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");

        assertEquals(3, queue.size());
        String sample = queue.sample();
        assertNotNull(sample);
        assertEquals("Size shouldn't change", 3, queue.size());

    }
 }