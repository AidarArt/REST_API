package www.artamonov.rest_task.service.mapper;

import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;

import java.util.List;

public interface PublishingHouseService {
    PublishingHouseEntity getById(Long id);
    List<PublishingHouseEntity> getAll();
    PublishingHouseEntity save(PublishingHouseEntity entity);
    PublishingHouseEntity update(Long id, PublishingHouseEntity entity);
    PublishingHouseEntity delete(Long id);
    List<BookEntity> getBooks(Long id);
}
