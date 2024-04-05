package www.artamonov.rest_task.servlet.mapper.impl;

import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.servlet.dto.BookIncomingDto;
import www.artamonov.rest_task.servlet.dto.BookOutGoingDto;
import www.artamonov.rest_task.servlet.dto.PublishingHouseOutGoingDto;
import www.artamonov.rest_task.servlet.mapper.BookDtoMapper;

public class BookDtoMapperImpl implements BookDtoMapper {

    @Override
    public BookEntity map(BookIncomingDto incomingDto) {
        BookEntity entity = new BookEntity();
        entity.setId(incomingDto.getId());
        entity.setName(incomingDto.getName());
        entity.setPublicationYear(incomingDto.getPublicationYear());
        entity.setPublishingHouse(incomingDto.getPublishingHouse());
        return entity;
    }

    @Override
    public BookOutGoingDto map(BookEntity entity) {
        PublishingHouseOutGoingDto publishingHouseDto = new PublishingHouseOutGoingDto();
        publishingHouseDto.setId(entity.getPublishingHouse().getId());
        publishingHouseDto.setName(entity.getPublishingHouse().getName());
        return new BookOutGoingDto(entity.getId(), entity.getName(), entity.getPublicationYear(), publishingHouseDto);
    }
}
