package www.artamonov.rest_task.service.impl;

import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.repository.mapper.BookRepository;
import www.artamonov.rest_task.repository.mapper.PublishingHouseRepository;
import www.artamonov.rest_task.service.mapper.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    public BookServiceImpl(BookRepository repository, PublishingHouseRepository publishingHouseRepository) {
        this.repository = repository;
        this.publishingHouseRepository = publishingHouseRepository;
    }

    private final PublishingHouseRepository publishingHouseRepository;
    @Override
    public BookEntity getById(Long id) {
        BookEntity entity = repository.findById(id);
        PublishingHouseEntity publishingHouse = publishingHouseRepository.findById(entity.getPublishingHouse().getId());
        entity.setPublishingHouse(publishingHouse);
        return entity;
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

    @Override
    public List<BookEntity> getAll() {
        List<BookEntity> entities = repository.findAll();
        for (BookEntity entity : entities) {
            PublishingHouseEntity publishingHouse = publishingHouseRepository.findById(entity.getPublishingHouse().getId());
            entity.setPublishingHouse(publishingHouse);
        }
        return entities;
    }

    @Override
    public List<AuthorEntity> getAuthors(Long id) {
        return repository.getAuthors(id);
    }
}
