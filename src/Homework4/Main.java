package Homework4;

public class Main {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Барсик");
        Cat cat2 = new Cat("Мурзик");
        Dog dog1 = new Dog("Шарик");
        Bowl bowl = new Bowl(10);

        System.out.println("--- Бег и плавание ---");
        cat1.run(150);
        cat1.run(250);
        cat1.swim(5);
        dog1.run(400);
        dog1.run(600);
        dog1.swim(5);
        dog1.swim(15);

        System.out.println("\n--- Кормление ---");
        cat1.eat(bowl, 5);
        cat2.eat(bowl, 6);

        System.out.println("\n--- Сытость ---");
        System.out.println(cat1.name + " сыт? " + cat1.isSatiety());
        System.out.println(cat2.name + " сыт? " + cat2.isSatiety());

        System.out.println("\n--- Добавление еды ---");
        bowl.addFood(10);
        cat2.eat(bowl, 6);

        System.out.println("\nВсего создано животных: " + Animal.getCountAnimals());
    }
}
