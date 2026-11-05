package Homework6;

import java.util.*;

public class MainStudents {
    public static void main(String[] args) {

        Set<Student> students = new HashSet<>();

        students.add(new Student("Иван", "Группа 1", 1, Arrays.asList(4, 5, 4)));
        students.add(new Student("Мария", "Группа 1", 1, Arrays.asList(3, 3, 3)));
        students.add(new Student("Петр", "Группа 2", 2, Arrays.asList(2, 2, 3)));
        students.add(new Student("Анна", "Группа 2", 2, Arrays.asList(5, 5, 5)));
        students.add(new Student("Сергей", "Группа 3", 3, Arrays.asList(1, 2, 2)));

        System.out.println("--- Все студенты изначально ---");
        printAll(students);

        System.out.println("\n--- Удаляем двоечников ---");
        removeBadStudents(students);
        printAll(students);

        System.out.println("\n--- Переводим на следующий курс ---");
        promoteStudents(students);
        printAll(students);

        System.out.println("\n--- Студенты 2-го курса ---");
        printStudents(students, 2);
    }

    public static void removeBadStudents(Set<Student> students) {
        // Используем итератор, чтобы безопасно удалять элементы из коллекции
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getAverageGrade() < 3) {
                iterator.remove();
            }
        }
    }

    public static void promoteStudents(Set<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3) {
                student.setCourse(student.getCourse() + 1);
            }
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
            }
        }
    }

    public static void printAll(Set<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}