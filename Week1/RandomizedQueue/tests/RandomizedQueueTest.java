import org.junit.Before;
import org.junit.Test;

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
        assertEquals(1, queue.size());
        //assertEquals("a",);
    }
}