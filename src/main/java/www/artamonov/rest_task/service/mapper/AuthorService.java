package www.artamonov.rest_task.service.mapper;

import www.artamonov.rest_task.model.AuthorEntity;

public interface AuthorService {
    AuthorEntity getById(Long id);
    AuthorEntity save(AuthorEntity entity);
    AuthorEntity update(Long id, AuthorEntity entity);
    AuthorEntity delete(Long id);
}
