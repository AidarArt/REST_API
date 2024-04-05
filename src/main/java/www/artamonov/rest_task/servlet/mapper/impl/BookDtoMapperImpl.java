package www.artamonov.rest_task.servlet.mapper.impl;

import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.servlet.dto.BookOutGoingDto;
import www.artamonov.rest_task.servlet.mapper.BookDtoMapper;

public class BookDtoMapperImpl implements BookDtoMapper {

    @Override
    public BookOutGoingDto map(BookEntity entity) {
        return new BookOutGoingDto(entity.getId(), entity.getName(), entity.getPublicationYear());
    }
}
