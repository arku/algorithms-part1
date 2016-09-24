import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by lazyass on 9/21/16.
 */
public class PointTest {
    private Point p1, p2, p3, p4, p5, p6, p7, p8, p9, p10;
    @Before
    public void setup() {
        p1 = new Point(0, 0);
        p2 = new Point(-1, -1);
        p3 = new Point(2, 3);
        p4 = new Point(-1, 2);
        p5 = new Point(3, -1);
        p6 = new Point(0, 0);
        p7 = new Point(3, 3);
        p8 = new Point(2, 1);
        p9 = new Point(19000, 10000);
        p10 = new Point(1234, 5678);
    }

    @Test
    public void checkEquality() {
        assertEquals(0, p1.compareTo(p6));
    }

    @Test
    public void checkLessThan() {
        assertEquals(-1, p2.compareTo(p3));
        assertEquals(-1, p3.compareTo(p7));
    }

    @Test
    public void checkGreaterThan() {
        assertEquals(1, p3.compareTo(p1));
    }

    @Test
    public void checkPositiveZero() {
        assertEquals((1.0 - 1.0) / 1.0, p3.slopeTo(p7), 0.001);
    }

    @Test
    public void checkPositiveInfSlope() {
        assertEquals(Double.POSITIVE_INFINITY, p3.slopeTo(p8), 0.001);
    }

    @Test
    public void checkNegativeInfSlope() {
        assertEquals(Double.NEGATIVE_INFINITY, p3.slopeTo(p3), 0.001);
    }

    @Test
    public void checkZeroSlope() {
        assertEquals(0, p2.slopeTo(p5), 0.001);
    }

    @Test
    public void testSlope() {
        assertEquals(-4.0, p5.slopeTo(p3), 0.001);
        assertEquals(0.2432736688, p9.slopeTo(p10), 0.001);
    }


}