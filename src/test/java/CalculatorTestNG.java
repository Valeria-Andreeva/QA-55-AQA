package Homework7;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class CalculatorTestNG {
    @Test
    public void testAdd() { assertEquals(Calculator.add(2, 3), 5); }
    @Test
    public void testSubtract() { assertEquals(Calculator.subtract(5, 3), 2); }
    @Test
    public void testMultiply() { assertEquals(Calculator.multiply(5, 3), 15); }
    @Test
    public void testDivide() { assertEquals(Calculator.divide(10, 5), 2.0); }
}