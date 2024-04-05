package www.artamonov.rest_task.servlet.mapper;

import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.servlet.dto.PublishingHouseIncomingDto;
import www.artamonov.rest_task.servlet.dto.PublishingHouseOutGoingDto;

public interface PublishingHouseDtoMapper {
    PublishingHouseEntity map(PublishingHouseIncomingDto dto);
    PublishingHouseOutGoingDto map(PublishingHouseEntity entity);
}
