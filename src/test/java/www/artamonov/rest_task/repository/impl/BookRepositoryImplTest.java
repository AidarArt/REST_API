package www.artamonov.rest_task.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import www.artamonov.rest_task.db.ConnectionManager;
import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.repository.mapper.BookRepository;
import www.artamonov.rest_task.repository.result_mapper.BookResultMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
class BookRepositoryImplTest {
    private final ConnectionManager connectionManager = Mockito.mock(ConnectionManager.class);
    private BookRepository repository;
    private final Connection connection = Mockito.mock(Connection.class);
    private BookEntity entity;

    @BeforeEach
    void setUp() throws SQLException {
        Mockito.when(connectionManager.getConnection()).thenReturn(connection);
        repository = new BookRepositoryImpl(connectionManager, new BookResultMapper());

        entity = new BookEntity(1L, "name", 1111, new PublishingHouseEntity());
        entity.getPublishingHouse().setId(1L);
    }

    @Test
    void create() throws SQLException {
        String query = "INSERT INTO book (name, year_of_publication, publishing_house_id) VALUES (?, ?, ?);";
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(query)).thenReturn(statement);
        repository.create(entity);
        Mockito.verify(statement).setString(1, "name");
        Mockito.verify(statement).setInt(2, 1111);
        Mockito.verify(statement).setLong(3, 1L);
        Mockito.verify(statement, Mockito.times(1)).executeUpdate();
    }

    @Test
    void findById() throws SQLException {
        String query = "SELECT * FROM book WHERE id = ?;";
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(query)).thenReturn(statement);

        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery()).thenReturn(resultSet);

        Mockito.when(resultSet.getLong(1)).thenReturn(1L);
        Mockito.when(resultSet.getString(2)).thenReturn("name");
        Mockito.when(resultSet.getInt(3)).thenReturn(1111);
        Mockito.when(resultSet.getLong(4)).thenReturn(1L);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

        repository.findById(1L);

        Mockito.verify(statement, Mockito.times(1)).executeQuery();
        Mockito.verify(statement, Mockito.times(1)).setLong(1, 1L);
    }

    @Test
    void update() throws SQLException {
        String query = "UPDATE book SET name = ?, year_of_publication = ?, publishing_house_id = ? WHERE id = ?;";
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(query)).thenReturn(statement);

        repository.update(1L, entity);

        Mockito.verify(statement, Mockito.times(1)).setString(1, "name");
        Mockito.verify(statement, Mockito.times(1)).setInt(2, 1111);
        Mockito.verify(statement, Mockito.times(1)).setLong(3, 1L);
        Mockito.verify(statement, Mockito.times(1)).setLong(4, 1L);
        Mockito.verify(statement, Mockito.times(1)).executeUpdate();
    }

    @Test
    void delete() throws SQLException {
        String query = "DELETE FROM book WHERE id =?;";
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(query)).thenReturn(statement);

        repository.delete(1L);

        Mockito.verify(statement, Mockito.times(1)).setLong(1, 1L);
        Mockito.verify(statement, Mockito.times(1)).executeUpdate();
    }
}
