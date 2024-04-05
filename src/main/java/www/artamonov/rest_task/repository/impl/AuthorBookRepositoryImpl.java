package www.artamonov.rest_task.repository.impl;

import www.artamonov.rest_task.db.ConnectionManager;
import www.artamonov.rest_task.db.PostgresConnectionManager;
import www.artamonov.rest_task.repository.mapper.AuthorBookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorBookRepositoryImpl implements AuthorBookRepository {

    private final ConnectionManager connectionManager = new PostgresConnectionManager();

    @Override
    public void addAuthorBook(Long bookId, Long authorId) {
        String query = "INSERT INTO author_book (book_id, author_id) VALUES (?, ?);" ;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bookId);
            statement.setLong(2, authorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAuthorBook(Long bookId, Long authorId) {
        String query = "DELETE FROM author_book WHERE book_id = ? AND author_id = ?;";
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, bookId);
            statement.setLong(2, authorId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
