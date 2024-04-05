package www.artamonov.rest_task.service.mapper;

import www.artamonov.rest_task.model.BookEntity;

public interface BookService {
    BookEntity getById(Long id);
    BookEntity save(BookEntity entity);
    BookEntity update(Long id, BookEntity entity);
    BookEntity delete(Long id);
}
