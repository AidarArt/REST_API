package www.artamonov.rest_task.repository.result_mapper;

import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookResultMapper implements ResultMapper<BookEntity> {
    @Override
    public BookEntity map(ResultSet resultSet) throws SQLException {
        BookEntity entity = new BookEntity();
        while (resultSet.next()) {
            entity.setId(resultSet.getLong(1));
            entity.setName(resultSet.getString(2));
            entity.setPublicationYear(resultSet.getInt(3));
            entity.setPublishingHouse(new PublishingHouseEntity());
            entity.getPublishingHouse().setId(resultSet.getLong(4));
        }
        return entity;
    }

    @Override
    public List<BookEntity> mapAll(ResultSet resultSet) throws SQLException {
        List<BookEntity> entities = new ArrayList<>();
        while (resultSet.next()) {
            BookEntity entity = new BookEntity();
            entity.setId(resultSet.getLong(1));
            entity.setName(resultSet.getString(2));
            entity.setPublicationYear(resultSet.getInt(3));
            entity.setPublishingHouse(new PublishingHouseEntity());
            entity.getPublishingHouse().setId(resultSet.getLong(4));
            entities.add(entity);
        }
        return entities;
    }
}
