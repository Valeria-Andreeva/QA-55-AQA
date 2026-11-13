package Homework7;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class NumberComparatorTestNG {
    @Test
    public void testGreater() { assertEquals(NumberComparator.compare(5, 3), "a > b"); }
    @Test
    public void testLess() { assertEquals(NumberComparator.compare(2, 10), "a < b"); }
    @Test
    public void testEqual() { assertEquals(NumberComparator.compare(5, 5), "a == b"); }
}