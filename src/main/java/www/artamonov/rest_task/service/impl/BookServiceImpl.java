package www.artamonov.rest_task.service.impl;

import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.repository.impl.BookRepositoryImpl;
import www.artamonov.rest_task.repository.mapper.BookRepository;
import www.artamonov.rest_task.service.mapper.BookService;

public class BookServiceImpl implements BookService {
    private final BookRepository repository = new BookRepositoryImpl();
    @Override
    public BookEntity getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public BookEntity save(BookEntity entity) {
        repository.create(entity);
        return entity;
    }

    @Override
    public BookEntity update(Long id, BookEntity entity) {
        repository.update(id, entity);
        return repository.findById(id);
    }

    @Override
    public BookEntity delete(Long id) {
        BookEntity entity = repository.findById(id);
        repository.delete(id);
        return entity;
    }
}
