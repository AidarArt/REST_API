package www.artamonov.rest_task.service.impl;

import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.repository.mapper.AuthorRepository;
import www.artamonov.rest_task.service.mapper.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AuthorEntity> getAll() {
        return repository.findAll();
    }

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

    @Override
    public List<BookEntity> getBooks(Long id) {
        return repository.getBooks(id);
    }
}
