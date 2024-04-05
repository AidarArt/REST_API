package www.artamonov.rest_task.repository.impl;

import www.artamonov.rest_task.db.ConnectionManager;
import www.artamonov.rest_task.db.PostgresConnectionManager;
import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.repository.mapper.BookRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private final ConnectionManager connectionManager = new PostgresConnectionManager();
    @Override
    public void create(BookEntity bookEntity) {
        String query = "INSERT INTO book (name, year_of_publication, publishing_house_id) VALUES (?, ?, ?);";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, bookEntity.getName());
            statement.setInt(2, bookEntity.getPublicationYear());
            statement.setLong(3, bookEntity.getPublishingHouse().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BookEntity findById(Long id) {
        String query = "SELECT * FROM book WHERE id = ?;";
        BookEntity entity = new BookEntity();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entity.setId(id);
                entity.setName(resultSet.getString(2));
                entity.setPublicationYear(resultSet.getInt(3));
                entity.setPublishingHouse(new PublishingHouseEntity());
                entity.getPublishingHouse().setId(resultSet.getLong(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public void update(Long id, BookEntity bookEntity) {
        String query = "UPDATE book SET name = ?, year_of_publication = ?, publishing_house_id = ? WHERE id = ?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, bookEntity.getName());
            statement.setInt(2, bookEntity.getPublicationYear());
            statement.setLong(3, bookEntity.getPublishingHouse().getId());
            statement.setLong(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM book WHERE id =?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BookEntity> findAll() {
        List<BookEntity> entities = new ArrayList<>();
        String query = "SELECT * FROM book;";
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                BookEntity entity = new BookEntity();
                entity.setId(resultSet.getLong(1));
                entity.setName(resultSet.getString(2));
                entity.setPublicationYear(resultSet.getInt(3));
                entity.setPublishingHouse(new PublishingHouseEntity());
                entity.getPublishingHouse().setId(resultSet.getLong(4));
                entities.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public List<AuthorEntity> getAuthors(Long id) {
        List<AuthorEntity> entities = new ArrayList<>();
        String query = "SELECT id, name, surname FROM author INNER JOIN author_book ab on author.id = ab.author_id WHERE book_id = ?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
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
}
