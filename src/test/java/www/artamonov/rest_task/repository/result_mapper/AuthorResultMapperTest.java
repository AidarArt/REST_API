package www.artamonov.rest_task.repository.result_mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import www.artamonov.rest_task.model.AuthorEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AuthorResultMapperTest {

    private ResultSet resultSet = Mockito.mock(ResultSet.class);
    private AuthorResultMapper resultMapper = new AuthorResultMapper();
    private AuthorEntity entity;
    private List<AuthorEntity> entities;

    @BeforeEach
    void setUp() throws SQLException {
        Mockito.when(resultSet.getLong(1)).thenReturn(1L);
        Mockito.when(resultSet.getString(2)).thenReturn("name");
        Mockito.when(resultSet.getString(3)).thenReturn("surname");
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

        entity = new AuthorEntity();
        entity.setId(1L);
        entity.setName("name");
        entity.setSurname("surname");

        entities = new ArrayList<>();
        entities.add(entity);
    }

    @Test
    void map() throws SQLException {
        Assertions.assertEquals(entity, resultMapper.map(resultSet));
    }

    @Test
    void mapAll() throws SQLException {
        Assertions.assertEquals(entities, resultMapper.mapAll(resultSet));
    }
}
