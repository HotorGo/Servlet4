package ru.mirea;

import java.util.List;

public interface TodoModel {

    List<TodoItem> getItems();

    void add(String text);

    void delete(int id);
}
