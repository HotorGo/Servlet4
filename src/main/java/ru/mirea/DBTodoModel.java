package ru.mirea;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBTodoModel implements TodoModel {


    @Override
    public List<TodoItem> getItems() {
        List<TodoItem> items = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/todo")) {
            String sql = "select id, text from todo order by id";
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                try (ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        int id = rs.getInt(1);
                        String text = rs.getString(2);
                        items.add(new TodoItem(id, text));
                    }
                }
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return items;
    }

    @Override
    public void add(String text) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/todo")) {
            String sql = "insert into todo (text) values(?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, text);
                ps.executeUpdate();
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/todo")) {
            String sql = "delete from todo where id=?";
            try (PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/todo")) {
            String sql = "create table todo (id int auto_increment primary key, text varchar(100))";
            try (Statement st = connection.createStatement()) {
                st.execute(sql);
            }
        }
    }
}
