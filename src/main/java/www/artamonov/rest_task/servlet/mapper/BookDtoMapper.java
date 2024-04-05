package www.artamonov.rest_task.servlet.mapper;

import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.servlet.dto.BookOutGoingDto;

public interface BookDtoMapper {
    BookOutGoingDto map(BookEntity entity);
}
