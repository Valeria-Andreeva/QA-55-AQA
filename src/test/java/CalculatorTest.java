package Homework7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    public void testAdd() { assertEquals(5, Calculator.add(2, 3)); }
    @Test
    public void testSubtract() { assertEquals(2, Calculator.subtract(5, 3)); }
    @Test
    public void testMultiply() { assertEquals(15, Calculator.multiply(5, 3)); }
    @Test
    public void testDivide() { assertEquals(2.0, Calculator.divide(10, 5)); }
}
