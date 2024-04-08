package www.artamonov.rest_task.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.postgresql.Driver;
import www.artamonov.rest_task.db.ConnectionManager;
import www.artamonov.rest_task.db.PostgresConnectionManager;
import www.artamonov.rest_task.repository.mapper.AuthorBookRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
class AuthorBookRepositoryImplTest {
    private final ConnectionManager connectionManager = Mockito.mock(ConnectionManager.class);
    private final AuthorBookRepository repository = new AuthorBookRepositoryImpl(connectionManager);
    private final Connection connection = Mockito.mock(Connection.class);
    private PreparedStatement statement;

    @BeforeEach
    void setUp() throws SQLException {
        Mockito.when(connectionManager.getConnection()).thenReturn(connection);
    }

    @Test
    void addAuthorBook() throws SQLException {
        String query = "INSERT INTO author_book (book_id, author_id) VALUES (?, ?);";
        statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(query)).thenReturn(statement);
        repository.addAuthorBook(1L, 1L);
        Mockito.verify(statement, Mockito.times(1)).executeUpdate();
        Mockito.verify(statement).setLong(1,1L);
        Mockito.verify(statement).setLong(2, 1L);
    }

    @Test
    void removeAuthorBook() throws SQLException {
        String query = "DELETE FROM author_book WHERE book_id = ? AND author_id = ?;";
        statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(query)).thenReturn(statement);
        repository.removeAuthorBook(1L, 1L);
        Mockito.verify(statement, Mockito.times(1)).executeUpdate();
        Mockito.verify(statement).setLong(1, 1L);
        Mockito.verify(statement).setLong(2, 1L);
    }

}
