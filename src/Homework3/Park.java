package Homework3;

public class Park {

    public class Attraction {
        private String name;
        private String workingHours;
        private double cost;

        public Attraction(String name, String workingHours, double cost) {
            this.name = name;
            this.workingHours = workingHours;
            this.cost = cost;
        }

        public void printInfo() {
            System.out.println("Аттракцион: " + name +
                    ", Время работы: " + workingHours +
                    ", Стоимость: " + cost);
        }
    }
}

