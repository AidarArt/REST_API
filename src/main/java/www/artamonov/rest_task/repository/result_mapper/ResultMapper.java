package www.artamonov.rest_task.repository.result_mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ResultMapper <T>{
    T map(ResultSet resultSet) throws SQLException;
    List<T> mapAll(ResultSet resultSet) throws SQLException;
}
