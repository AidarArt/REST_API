package www.artamonov.rest_task.servlet.mapper;

import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.servlet.dto.AuthorIncomingDto;
import www.artamonov.rest_task.servlet.dto.AuthorOutGoingDto;

public interface AuthorDtoMapper {
    AuthorEntity map(AuthorIncomingDto incomingDto);
    AuthorOutGoingDto map(AuthorEntity entity);
}
