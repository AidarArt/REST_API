package www.artamonov.rest_task.repository.mapper;

import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.model.BookEntity;

import java.util.List;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    List<AuthorEntity> findAll();
    List<BookEntity> getBooks(Long id);
}
