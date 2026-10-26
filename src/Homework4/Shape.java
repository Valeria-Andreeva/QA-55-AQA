package Homework4;

public interface Shape {
    double getPerimeter();
    double getArea();
    String getFillColor();
    String getBorderColor();

    default void printInfo() {
        System.out.println("Фигура: " + this.getClass().getSimpleName() +
                ", Периметр: " + getPerimeter() +
                ", Площадь: " + getArea() +
                ", Цвет фона: " + getFillColor() +
                ", Цвет границы: " + getBorderColor());
    }
}
