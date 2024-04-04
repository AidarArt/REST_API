package www.artamonov.rest_task.db;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionManager implements ConnectionManager{

    @Override
    public Connection getConnection() throws SQLException {
        String jdbcUrl = DBConfigReader.getUrl();
        String username = DBConfigReader.getUsername();
        String password = DBConfigReader.getPassword();

        DriverManager.registerDriver(new Driver());
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}
