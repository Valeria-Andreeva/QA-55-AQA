package Homework4;

public class Cat extends Animal {
    private boolean satiety = false;

    public Cat(String name) {
        super(name);
    }

    @Override
    public void run(int distance) {
        if (distance <= 200) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м. (максимум 200 м).");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать!");
    }

    public void eat(Bowl bowl, int amount) {
        if (bowl.getFood() >= amount) {
            bowl.decreaseFood(amount);
            satiety = true;
            System.out.println(name + " поел " + amount + " еды и наелся.");
        } else {
            System.out.println(name + " не смог поесть, в миске недостаточно еды.");
        }
    }

    public boolean isSatiety() {
        return satiety;
    }
}

