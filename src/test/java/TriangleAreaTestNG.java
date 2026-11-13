package Homework7;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class TriangleAreaTestNG {
    @Test
    public void testTriangleArea() {
        assertEquals(TriangleArea.calculate(3, 4, 5), 6.0, 0.0001);
    }
}