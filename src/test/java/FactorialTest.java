package Homework7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialTest {
    @Test
    public void testFactorial() {
        assertEquals(120, Factorial.calculate(5));
        assertEquals(1, Factorial.calculate(0));
    }
}