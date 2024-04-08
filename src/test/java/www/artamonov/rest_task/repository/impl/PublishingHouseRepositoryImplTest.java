package www.artamonov.rest_task.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import www.artamonov.rest_task.db.ConnectionManager;
import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.repository.mapper.PublishingHouseRepository;
import www.artamonov.rest_task.repository.result_mapper.PublishingHouseResultMapper;

import java.sql.*;

@ExtendWith(MockitoExtension.class)
class PublishingHouseRepositoryImplTest {
    private final ConnectionManager connectionManager = Mockito.mock(ConnectionManager.class);
    private PublishingHouseRepository repository;
    private final Connection connection = Mockito.mock(Connection.class);
    private PublishingHouseEntity entity;

    @BeforeEach
    void setUp() throws SQLException {
        Mockito.when(connectionManager.getConnection()).thenReturn(connection);
        repository = new PublishingHouseRepositoryImpl(connectionManager, new PublishingHouseResultMapper());

        entity = new PublishingHouseEntity(1L, "name");
    }

    @Test
    void create() throws SQLException {
        String query = "INSERT INTO publishing_house (name) VALUES (?);";
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(query)).thenReturn(statement);
        repository.create(entity);
        Mockito.verify(statement).setString(1, "name");
        Mockito.verify(statement, Mockito.times(1)).executeUpdate();
    }

    @Test
    void findById() throws SQLException {
        String query = "SELECT * FROM publishing_house WHERE id = ?;";
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(query)).thenReturn(statement);

        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery()).thenReturn(resultSet);

        Mockito.when(resultSet.getLong(1)).thenReturn(1L);
        Mockito.when(resultSet.getString(2)).thenReturn("name");
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

        repository.findById(1L);

        Mockito.verify(statement, Mockito.times(1)).executeQuery();
        Mockito.verify(statement, Mockito.times(1)).setLong(1, 1L);
    }

    @Test
    void update() throws SQLException {
        String query = "UPDATE publishing_house SET name = ? WHERE id = ?;";
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(query)).thenReturn(statement);

        repository.update(1L, entity);

        Mockito.verify(statement, Mockito.times(1)).setString(1, "name");
        Mockito.verify(statement, Mockito.times(1)).setLong(2, 1L);
        Mockito.verify(statement, Mockito.times(1)).executeUpdate();
    }

    @Test
    void delete() throws SQLException {
        String query = "DELETE FROM publishing_house WHERE id =?;";
        PreparedStatement statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(query)).thenReturn(statement);

        repository.delete(1L);

        Mockito.verify(statement, Mockito.times(1)).setLong(1, 1L);
        Mockito.verify(statement, Mockito.times(1)).executeUpdate();
    }

    @Test
    void findAll() throws SQLException {
        String query = "SELECT * FROM publishing_house;";
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);

        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery(query)).thenReturn(resultSet);

        Mockito.when(resultSet.getLong(1)).thenReturn(1L);
        Mockito.when(resultSet.getString(2)).thenReturn("name");
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

        repository.findAll();

        Mockito.verify(statement, Mockito.times(1)).executeQuery(query);
    }
}
