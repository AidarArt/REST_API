package www.artamonov.rest_task.repository.impl;

import www.artamonov.rest_task.db.ConnectionManager;
import www.artamonov.rest_task.db.PostgresConnectionManager;
import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.repository.mapper.AuthorRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<AuthorEntity> findAll() {
        List<AuthorEntity> entities = new ArrayList<>();
        String query = "SELECT * FROM author;";
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                AuthorEntity entity = new AuthorEntity();
                entity.setId(resultSet.getLong(1));
                entity.setName(resultSet.getString(2));
                entity.setSurname(resultSet.getString(3));
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
        String query = "SELECT book.id, book.name, book.year_of_publication, ph.id, ph.name FROM book " +
                "INNER JOIN publishing_house ph on book.publishing_house_id = ph.id " +
                "INNER JOIN author_book ab on book.id = ab.book_id " +
                "WHERE author_id = ?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BookEntity entity = new BookEntity();
                entity.setId(resultSet.getLong(1));
                entity.setName(resultSet.getString(2));
                entity.setPublicationYear(resultSet.getInt(3));
                PublishingHouseEntity publishingHouse = new PublishingHouseEntity();
                publishingHouse.setId(resultSet.getLong(4));
                publishingHouse.setName(resultSet.getString(5));
                entity.setPublishingHouse(publishingHouse);
                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }
}
