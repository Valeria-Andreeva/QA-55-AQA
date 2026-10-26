package Homework4;

public class MainShapes {
    public static void main(String[] args) {
        Shape circle = new Circle(5, "Красный", "Черный");
        Shape rectangle = new Rectangle(4, 6, "Синий", "Белый");
        Shape triangle = new Triangle(3, 4, 5, "Зеленый", "Желтый");

        circle.printInfo();
        rectangle.printInfo();
        triangle.printInfo();
    }
}
