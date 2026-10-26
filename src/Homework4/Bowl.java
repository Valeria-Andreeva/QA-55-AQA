package Homework4;

public class Bowl {
    private int food;

    public Bowl(int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public void decreaseFood(int amount) {
        food -= amount;
    }

    public void addFood(int amount) {
        food += amount;
        System.out.println("В миску добавили " + amount + " еды. Теперь в миске: " + food);
    }
}



