package www.artamonov.rest_task.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.repository.mapper.PublishingHouseRepository;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class PublishingHouseServiceImplTest {

    private final PublishingHouseRepository repository = Mockito.mock(PublishingHouseRepository.class);

    private final PublishingHouseServiceImpl service = new PublishingHouseServiceImpl(repository);

    private PublishingHouseEntity entity;

    @BeforeEach
    void setUp() {
        entity = new PublishingHouseEntity();
    }

    @Test
    void findById() {
        service.getById(1L);
        Mockito.verify(repository, Mockito.times(1)).findById(1L);
    }

    @Test
    void getAll() {
        Assertions.assertEquals(service.getAll(), new ArrayList<PublishingHouseEntity>());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    void save() {
        Assertions.assertEquals(service.save(entity), entity);
        Mockito.verify(repository, Mockito.times(1)).create(entity);
        Mockito.verify(repository).create(entity);
    }

    @Test
    void update() {
        service.update(entity.getId(), entity);
        Mockito.verify(repository, Mockito.times(1)).update(entity.getId(), entity);
        Mockito.verify(repository, Mockito.times(1)).findById(entity.getId());
    }

    @Test
    void delete() {
        service.delete(entity.getId());
        Mockito.verify(repository, Mockito.times(1)).delete(entity.getId());
    }

    @Test
    void getBooks() {
        Assertions.assertEquals(service.getBooks(entity.getId()), new ArrayList<BookEntity>());
        Mockito.verify(repository, Mockito.times(1)).getBooks(entity.getId());
    }
}
