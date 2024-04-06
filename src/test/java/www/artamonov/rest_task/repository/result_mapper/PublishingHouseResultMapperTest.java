package www.artamonov.rest_task.repository.result_mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import www.artamonov.rest_task.model.PublishingHouseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PublishingHouseResultMapperTest {
    private final PublishingHouseResultMapper resultMapper = new PublishingHouseResultMapper();
    private final ResultSet resultSet = Mockito.mock(ResultSet.class);
    private PublishingHouseEntity entity;
    private List<PublishingHouseEntity> entities;

    @BeforeEach
    void setUp() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getLong(1)).thenReturn(1L);
        Mockito.when(resultSet.getString(2)).thenReturn("name");

        entity = new PublishingHouseEntity();
        entity.setId(1L);
        entity.setName("name");

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
