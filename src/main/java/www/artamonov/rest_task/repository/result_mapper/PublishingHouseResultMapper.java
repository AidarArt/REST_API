package www.artamonov.rest_task.repository.result_mapper;

import www.artamonov.rest_task.model.PublishingHouseEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublishingHouseResultMapper implements ResultMapper<PublishingHouseEntity> {
    @Override
    public PublishingHouseEntity map(ResultSet resultSet) throws SQLException {
        PublishingHouseEntity entity = new PublishingHouseEntity();
        while (resultSet.next()) {
            entity.setId(resultSet.getLong(1));
            entity.setName(resultSet.getString(2));
        }
        return entity;
    }

    @Override
    public List<PublishingHouseEntity> mapAll(ResultSet resultSet)  throws SQLException {
        List<PublishingHouseEntity> entities = new ArrayList<>();
        while (resultSet.next()) {
            PublishingHouseEntity entity = new PublishingHouseEntity();
            entity.setId(resultSet.getLong(1));
            entity.setName(resultSet.getString(2));
            entities.add(entity);
        }
        return entities;
    }
}
