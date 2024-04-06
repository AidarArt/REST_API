package www.artamonov.rest_task.repository.result_mapper;

import www.artamonov.rest_task.model.AuthorEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorResultMapper implements ResultMapper<AuthorEntity> {
    @Override
    public AuthorEntity map(ResultSet resultSet) throws SQLException {
        AuthorEntity entity = new AuthorEntity();
        while (resultSet.next()) {
            entity.setId(resultSet.getLong(1));
            entity.setName(resultSet.getString(2));
            entity.setSurname(resultSet.getString(3));
        }
        return entity;
    }

    @Override
    public List<AuthorEntity> mapAll(ResultSet resultSet) throws SQLException {
        List<AuthorEntity> entities = new ArrayList<>();
        while (resultSet.next()) {
            AuthorEntity entity = new AuthorEntity();
            entity.setId(resultSet.getLong(1));
            entity.setName(resultSet.getString(2));
            entity.setSurname(resultSet.getString(3));
            entities.add(entity);
        }
        return entities;
    }
}
