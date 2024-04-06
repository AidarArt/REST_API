package www.artamonov.rest_task.service.impl;

import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.repository.mapper.PublishingHouseRepository;
import www.artamonov.rest_task.service.mapper.PublishingHouseService;

import java.util.List;

public class PublishingHouseServiceImpl implements PublishingHouseService {

    private final PublishingHouseRepository repository;

    public PublishingHouseServiceImpl(PublishingHouseRepository repository) {
        this.repository = repository;
    }

    @Override
    public PublishingHouseEntity getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<PublishingHouseEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public PublishingHouseEntity save(PublishingHouseEntity entity) {
        repository.create(entity);
        return entity;
    }

    @Override
    public PublishingHouseEntity update(Long id, PublishingHouseEntity entity) {
        repository.update(id, entity);
        return repository.findById(id);
    }

    @Override
    public PublishingHouseEntity delete(Long id) {
        PublishingHouseEntity entity = repository.findById(id);
        repository.delete(id);
        return entity;
    }

    @Override
    public List<BookEntity> getBooks(Long id) {
        return repository.getBooks(id);
    }
}
