package www.artamonov.rest_task.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.repository.mapper.AuthorRepository;
import www.artamonov.rest_task.service.mapper.AuthorService;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    private final AuthorRepository repository = Mockito.mock(AuthorRepository.class);
    private final AuthorService service = new AuthorServiceImpl(repository);
    private AuthorEntity entity;

    @BeforeEach
    void setUp() {
        entity = new AuthorEntity();
    }

    @Test
    void getAll() {
        service.getAll();
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    void getById() {
        service.getById(entity.getId());
        Mockito.verify(repository, Mockito.times(1)).findById(entity.getId());
        Mockito.verify(repository).findById(entity.getId());
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
        Mockito.verify(repository).update(entity.getId(), entity);
    }

    @Test
    void delete() {
        service.delete(entity.getId());
        Mockito.verify(repository, Mockito.times(1)).delete(entity.getId());
        Mockito.verify(repository).delete(entity.getId());
    }

    @Test
    void getBooks() {
        service.getBooks(entity.getId());
        Mockito.verify(repository, Mockito.times(1)).getBooks(entity.getId());
        Mockito.verify(repository).getBooks(entity.getId());
    }
}
