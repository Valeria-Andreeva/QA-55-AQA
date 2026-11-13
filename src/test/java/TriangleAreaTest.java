package Homework7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TriangleAreaTest {
    @Test
    public void testTriangleArea() {
        assertEquals(6.0, TriangleArea.calculate(3, 4, 5), 0.0001);
    }
}