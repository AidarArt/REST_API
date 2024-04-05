package www.artamonov.rest_task.servlet.mapper.impl;

import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.servlet.dto.PublishingHouseIncomingDto;
import www.artamonov.rest_task.servlet.dto.PublishingHouseOutGoingDto;
import www.artamonov.rest_task.servlet.mapper.PublishingHouseDtoMapper;

public class PublishingHouseDtoMapperImpl implements PublishingHouseDtoMapper {
    @Override
    public PublishingHouseEntity map(PublishingHouseIncomingDto dto) {
        PublishingHouseEntity entity = new PublishingHouseEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    @Override
    public PublishingHouseOutGoingDto map(PublishingHouseEntity entity) {
        return new PublishingHouseOutGoingDto(entity.getId(), entity.getName());
    }
}
