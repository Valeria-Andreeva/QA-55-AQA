package Homework7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberComparatorTest {
    @Test
    public void testGreater() { assertEquals("a > b", NumberComparator.compare(5, 3)); }
    @Test
    public void testLess() { assertEquals("a < b", NumberComparator.compare(2, 10)); }
    @Test
    public void testEqual() { assertEquals("a == b", NumberComparator.compare(5, 5)); }
}