package www.artamonov.rest_task.repository.impl;

import www.artamonov.rest_task.db.ConnectionManager;
import www.artamonov.rest_task.db.PostgresConnectionManager;
import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.repository.mapper.AuthorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRepositoryImpl implements AuthorRepository {

    private final ConnectionManager connectionManager = new PostgresConnectionManager();
    @Override
    public void create(AuthorEntity authorEntity) {
        String query = "INSERT INTO author (name, surname) VALUES (?, ?);";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, authorEntity.getName());
            statement.setString(2, authorEntity.getSurname());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AuthorEntity findById(Long id) {
        String query = "SELECT * FROM author WHERE id = ?";
        AuthorEntity entity = new AuthorEntity();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entity.setId(resultSet.getLong(1));
                entity.setName(resultSet.getString(2));
                entity.setSurname(resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void update(Long id, AuthorEntity authorEntity) {
        String query = "UPDATE author SET name = ?, surname = ? WHERE id = ?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, authorEntity.getName());
            statement.setString(2, authorEntity.getSurname());
            statement.setLong(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM author WHERE id =?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
