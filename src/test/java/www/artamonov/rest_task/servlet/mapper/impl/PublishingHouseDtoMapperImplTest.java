package www.artamonov.rest_task.servlet.mapper.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.servlet.dto.PublishingHouseIncomingDto;
import www.artamonov.rest_task.servlet.dto.PublishingHouseOutGoingDto;
import www.artamonov.rest_task.servlet.mapper.PublishingHouseDtoMapper;

class PublishingHouseDtoMapperImplTest {
    private final PublishingHouseDtoMapper dtoMapper = new PublishingHouseDtoMapperImpl();
    private PublishingHouseEntity entity;
    private PublishingHouseIncomingDto incomingDto;
    private PublishingHouseOutGoingDto outGoingDto;

    @BeforeEach
    void setUp() {
        entity = new PublishingHouseEntity();
        entity.setId(1L);
        entity.setName("name");

        incomingDto = new PublishingHouseIncomingDto();
        incomingDto.setId(1L);
        incomingDto.setName("name");

        outGoingDto = new PublishingHouseOutGoingDto();
        outGoingDto.setId(1L);
        outGoingDto.setName("name");
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
