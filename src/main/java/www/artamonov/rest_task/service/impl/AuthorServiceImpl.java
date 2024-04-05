package www.artamonov.rest_task.service.impl;

import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.repository.impl.AuthorRepositoryImpl;
import www.artamonov.rest_task.repository.mapper.AuthorRepository;
import www.artamonov.rest_task.service.mapper.AuthorService;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository = new AuthorRepositoryImpl();

    @Override
    public AuthorEntity getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public AuthorEntity save(AuthorEntity entity) {
        repository.create(entity);
        return entity;
    }

    @Override
    public AuthorEntity update(Long id, AuthorEntity entity) {
        repository.update(id, entity);
        return repository.findById(id);
    }

    @Override
    public AuthorEntity delete(Long id) {
        AuthorEntity entity = repository.findById(id);
        repository.delete(id);
        return entity;
    }
}
