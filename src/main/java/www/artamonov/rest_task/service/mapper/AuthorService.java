package www.artamonov.rest_task.service.mapper;

import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.model.BookEntity;

import java.util.List;

public interface AuthorService {
    List<AuthorEntity> getAll();
    AuthorEntity getById(Long id);
    AuthorEntity save(AuthorEntity entity);
    AuthorEntity update(Long id, AuthorEntity entity);
    AuthorEntity delete(Long id);
    List<BookEntity> getBooks(Long id);
}
