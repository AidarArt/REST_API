package www.artamonov.rest_task.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.repository.mapper.BookRepository;
import www.artamonov.rest_task.repository.mapper.PublishingHouseRepository;
import www.artamonov.rest_task.service.mapper.BookService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    private final BookRepository repository = Mockito.mock(BookRepository.class);
    private final PublishingHouseRepository publishingHouseRepository = Mockito.mock(PublishingHouseRepository.class);
    private final BookService service = new BookServiceImpl(repository, publishingHouseRepository);
    private BookEntity entity;

    @BeforeEach
    void setUp() {
        entity = new BookEntity();
        PublishingHouseEntity publishingHouse = new PublishingHouseEntity();
        entity.setPublishingHouse(publishingHouse);
        List<BookEntity> entities = new ArrayList<>();
        entities.add(entity);
        Mockito.when(repository.findById(entity.getId())).thenReturn(entity);
        Mockito.when(repository.findAll()).thenReturn(entities);

    }

    @Test
    void getById() {
        BookEntity entity1 = service.getById(entity.getId());
        Mockito.verify(repository, Mockito.times(1)).findById(entity.getId());
        Mockito.verify(publishingHouseRepository, Mockito.times(1)).findById(entity1.getId());
        Mockito.verify(repository).findById(entity.getId());
    }

    @Test
    void getAll() {
        service.getAll();
        Mockito.verify(repository, Mockito.times(1)).findAll();
        Mockito.verify(publishingHouseRepository, Mockito.times(1)).findById(entity.getId());
    }

    @Test
    void save() {
        service.save(entity);
        Mockito.verify(repository, Mockito.times(1)).create(entity);
        Mockito.verify(repository).create(entity);
    }

    @Test
    void update() {
        service.update(entity.getId(), entity);
        Mockito.verify(repository, Mockito.times(1)).update(entity.getId(), entity);
        Mockito.verify(repository).update(entity.getId(), entity);
    }

    @Test
    void delete() {
        service.delete(entity.getId());
        Mockito.verify(repository, Mockito.times(1)).delete(entity.getId());
        Mockito.verify(repository).delete(entity.getId());
    }

    @Test
    void getAuthors() {
        service.getAuthors(entity.getId());
        Mockito.verify(repository, Mockito.times(1)).getAuthors(entity.getId());
        Mockito.verify(repository).getAuthors(entity.getId());
    }
}
