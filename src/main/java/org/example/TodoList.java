package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TodoList {
    private final List<String> items = new ArrayList<>();
    private final Set<Integer> completed = new HashSet<>();

    public void add(String item) {
        if (item != null) {
            item = item.trim();
            if (!item.isEmpty()) {
                items.add(item);
            }
        }
    }

    public boolean remove(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);


            Set<Integer> shifted = new HashSet<>();
            for (int i : completed) {
                if (i < index) shifted.add(i);
                else if (i > index) shifted.add(i - 1);

            }
            completed.clear();
            completed.addAll(shifted);
            return true;
        }
        return false;
    }

    public List<String> getAll() {
        return new ArrayList<>(items);
    }

    public int size() {
        return items.size();
    }

    // --- методы ДЗ ---

    public void clear() {
        items.clear();
        completed.clear();
    }

    public boolean markDone(int index) {
        if (index >= 0 && index < items.size()) {
            completed.add(index);
            return true;
        }
        return false;
    }

    public boolean isDone(int index) {
        return completed.contains(index);
    }

    public List<String> search(String substring) {
        List<String> result = new ArrayList<>();
        if (substring == null) return result;
        String needle = substring.trim().toLowerCase();
        if (needle.isEmpty()) return result;

        for (String item : items) {
            if (item.toLowerCase().contains(needle)) {
                result.add(item);
            }
        }
        return result;
    }
}