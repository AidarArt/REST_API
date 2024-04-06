package www.artamonov.rest_task.servlet.mapper.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.servlet.dto.BookIncomingDto;
import www.artamonov.rest_task.servlet.dto.BookOutGoingDto;
import www.artamonov.rest_task.servlet.dto.PublishingHouseOutGoingDto;
import www.artamonov.rest_task.servlet.mapper.BookDtoMapper;

class BookDtoMapperImplTest {

    private final BookDtoMapper dtoMapper = new BookDtoMapperImpl();
    private BookEntity entity;
    private BookIncomingDto incomingDto;
    private BookOutGoingDto outGoingDto;

    @BeforeEach
    void setUp() {
        entity = new BookEntity();
        entity.setId(1L);
        entity.setName("name");
        entity.setPublicationYear(1111);
        entity.setPublishingHouse(new PublishingHouseEntity());

        incomingDto = new BookIncomingDto();
        incomingDto.setId(1L);
        incomingDto.setName("name");
        incomingDto.setPublicationYear(1111);
        incomingDto.setPublishingHouse(new PublishingHouseEntity());

        outGoingDto = new BookOutGoingDto();
        outGoingDto.setId(1L);
        outGoingDto.setName("name");
        outGoingDto.setPublicationYear(1111);
        outGoingDto.setPublishingHouse(new PublishingHouseOutGoingDto());
    }

    @Test
    void mapToEntity() {
        Assertions.assertEquals(entity, dtoMapper.map(incomingDto));
    }

    @Test
    void mapToOutGoingDto() {
        Assertions.assertEquals(outGoingDto, dtoMapper.map(entity));
    }
}
