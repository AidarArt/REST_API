package www.artamonov.rest_task.repository.mapper;

import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.model.BookEntity;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, Long> {
    List<BookEntity> findAll();
    List<AuthorEntity> getAuthors(Long id);
}
