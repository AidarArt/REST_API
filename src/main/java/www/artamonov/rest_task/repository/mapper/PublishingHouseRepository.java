package www.artamonov.rest_task.repository.mapper;

import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;

import java.util.List;

public interface PublishingHouseRepository extends CrudRepository<PublishingHouseEntity, Long>{
    List<PublishingHouseEntity> findAll();
    List<BookEntity> getBooks(Long id);
}
