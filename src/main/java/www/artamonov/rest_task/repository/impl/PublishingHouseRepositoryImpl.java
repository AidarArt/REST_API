package www.artamonov.rest_task.repository.impl;

import www.artamonov.rest_task.db.ConnectionManager;
import www.artamonov.rest_task.db.PostgresConnectionManager;
import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.repository.mapper.PublishingHouseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublishingHouseRepositoryImpl implements PublishingHouseRepository {

    private final ConnectionManager connectionManager = new PostgresConnectionManager();
    public void create(PublishingHouseEntity publishingHouseEntity) {
        String query = "INSERT INTO publishing_house (name) VALUES (?);";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, publishingHouseEntity.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PublishingHouseEntity findById(Long id) {
        String query = "SELECT * FROM publishing_house WHERE id = ?;";
        PublishingHouseEntity entity = new PublishingHouseEntity();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entity.setId(id);
                entity.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public void update(Long id, PublishingHouseEntity publishingHouseEntity) {
        String query = "UPDATE publishing_house SET name = ? WHERE id = ?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, publishingHouseEntity.getName());
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        String query = "DELETE FROM publishing_house WHERE id =?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PublishingHouseEntity> findAll() {
        List<PublishingHouseEntity> entities = new ArrayList<>();
        String query = "SELECT * FROM publishing_house;";
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                PublishingHouseEntity entity = new PublishingHouseEntity();
                entity.setId(resultSet.getLong(1));
                entity.setName(resultSet.getString(2));
                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public List<BookEntity> getBooks(Long id) {
        List<BookEntity> entities = new ArrayList<>();
        String query = "SELECT * FROM book WHERE publishing_house_id = ?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BookEntity entity = new BookEntity();
                entity.setId(resultSet.getLong(1));
                entity.setName(resultSet.getString(2));
                entity.setPublicationYear(resultSet.getInt(3));
                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }
}
