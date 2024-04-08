package www.artamonov.rest_task.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import www.artamonov.rest_task.db.ConnectionManager;
import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.repository.mapper.AuthorRepository;
import www.artamonov.rest_task.repository.result_mapper.AuthorResultMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
class AuthorRepositoryImplTest {
    private final ConnectionManager connectionManager = Mockito.mock(ConnectionManager.class);
    private AuthorRepository repository;
    private final Connection connection = Mockito.mock(Connection.class);
    private AuthorEntity entity;

    @BeforeEach
    void setUp() throws SQLException {
        Mockito.when(connectionManager.getConnection()).thenReturn(connection);
        repository = new AuthorRepositoryImpl(connectionManager, new AuthorResultMapper());

        entity = new AuthorEntity(1L, "name", "surname");
    }

    @Test
    void create() throws SQLException {
        String query = "INSERT INTO author (name, surname) VALUES (?, ?);";
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(query)).thenReturn(statement);
        repository.create(entity);
        Mockito.verify(statement).setString(1, "name");
        Mockito.verify(statement).setString(2, "surname");
        Mockito.verify(statement, Mockito.times(1)).executeUpdate();
    }

    @Test
    void findById() throws SQLException {
        String query = "SELECT * FROM author WHERE id = ?";
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(query)).thenReturn(statement);

        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery()).thenReturn(resultSet);

        Mockito.when(resultSet.getLong(1)).thenReturn(1L);
        Mockito.when(resultSet.getString(2)).thenReturn("name");
        Mockito.when(resultSet.getString(3)).thenReturn("surname");
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

        repository.findById(1L);

        Mockito.verify(statement, Mockito.times(1)).executeQuery();
        Mockito.verify(statement, Mockito.times(1)).setLong(1, 1L);
    }

    @Test
    void update() throws SQLException {
        String query = "UPDATE author SET name = ?, surname = ? WHERE id = ?;";
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(query)).thenReturn(statement);

        repository.update(1L, entity);

        Mockito.verify(statement, Mockito.times(1)).setString(1, "name");
        Mockito.verify(statement, Mockito.times(1)).setString(2, "surname");
        Mockito.verify(statement, Mockito.times(1)).setLong(3, 1L);
        Mockito.verify(statement, Mockito.times(1)).executeUpdate();
    }

    @Test
    void delete() throws SQLException {
        String query = "DELETE FROM author WHERE id =?;";
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(query)).thenReturn(statement);

        repository.delete(1L);

        Mockito.verify(statement, Mockito.times(1)).setLong(1, 1L);
        Mockito.verify(statement, Mockito.times(1)).executeUpdate();
    }
}
