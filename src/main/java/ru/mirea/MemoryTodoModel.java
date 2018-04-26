package ru.mirea;

import java.util.ArrayList;
import java.util.List;

public class MemoryTodoModel implements TodoModel {

    private List<TodoItem> items = new ArrayList<>();
    private int count = 1;

    @Override
    public List<TodoItem> getItems() {
        return items;
    }

    @Override
    public void add(String text) {
        items.add(new TodoItem(count, text));
        count++;
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < items.size(); i++) {
            TodoItem item = items.get(i);
            if (item.getId() == id) {
                items.remove(i);
                return;
            }
        }
    }
}
