package www.artamonov.rest_task.repository.result_mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BookResultMapperTest {

    private final BookResultMapper resultMapper = new BookResultMapper();
    private final ResultSet resultSet = Mockito.mock(ResultSet.class);
    private BookEntity entity;
    private List<BookEntity> entities;

    @BeforeEach
    void setUp() throws SQLException {
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getLong(1)).thenReturn(1L);
        Mockito.when(resultSet.getString(2)).thenReturn("book");
        Mockito.when(resultSet.getInt(3)).thenReturn(1);
        Mockito.when(resultSet.getLong(4)).thenReturn(2L);

        entity = new BookEntity();
        entity.setId(1L);
        entity.setName("book");
        entity.setPublicationYear(1);
        entity.setPublishingHouse(new PublishingHouseEntity());
        entity.getPublishingHouse().setId(2L);

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
