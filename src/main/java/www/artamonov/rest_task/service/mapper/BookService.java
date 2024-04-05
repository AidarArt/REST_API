package www.artamonov.rest_task.service.mapper;

import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.model.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity getById(Long id);
    BookEntity save(BookEntity entity);
    BookEntity update(Long id, BookEntity entity);
    BookEntity delete(Long id);
    List<BookEntity> getAll();
    List<AuthorEntity> getAuthors(Long id);
}
