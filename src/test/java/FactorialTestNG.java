package Homework7;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class FactorialTestNG {
    @Test
    public void testFactorial() {
        assertEquals(Factorial.calculate(5), 120);
        assertEquals(Factorial.calculate(0), 1);
    }
}