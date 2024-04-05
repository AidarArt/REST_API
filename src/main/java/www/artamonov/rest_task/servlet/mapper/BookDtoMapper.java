package www.artamonov.rest_task.servlet.mapper;

import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.servlet.dto.BookIncomingDto;
import www.artamonov.rest_task.servlet.dto.BookOutGoingDto;

public interface BookDtoMapper {
    BookEntity map(BookIncomingDto incomingDto);
    BookOutGoingDto map(BookEntity entity);
}
