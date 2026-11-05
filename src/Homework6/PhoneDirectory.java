package Homework6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneDirectory {

    private Map<String, List<String>> directory = new HashMap<>();

    public void add(String surname, String phone) {

        if (!directory.containsKey(surname)) {
            directory.put(surname, new ArrayList<>());
        }

        directory.get(surname).add(phone);
        System.out.println("Добавлен номер " + phone + " для " + surname);
    }

    public List<String> get(String surname) {
        return directory.getOrDefault(surname, new ArrayList<>());
    }
}