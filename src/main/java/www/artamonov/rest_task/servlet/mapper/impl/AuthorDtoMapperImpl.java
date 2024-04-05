package www.artamonov.rest_task.servlet.mapper.impl;

import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.servlet.dto.AuthorIncomingDto;
import www.artamonov.rest_task.servlet.dto.AuthorOutGoingDto;
import www.artamonov.rest_task.servlet.mapper.AuthorDtoMapper;

public class AuthorDtoMapperImpl implements AuthorDtoMapper {
    @Override
    public AuthorEntity map(AuthorIncomingDto incomingDto) {
        AuthorEntity entity = new AuthorEntity();
        entity.setName(incomingDto.getName());
        entity.setSurname(incomingDto.getSurname());
        return entity;
    }

    @Override
    public AuthorOutGoingDto map(AuthorEntity entity) {
        return new AuthorOutGoingDto(entity.getId(), entity.getName(), entity.getSurname());
    }
}
