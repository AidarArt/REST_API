package www.artamonov.rest_task.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class PostgresConnectionManagerTest {
    private final PostgresConnectionManager connectionManager = new PostgresConnectionManager();

    @Test
    void getConnection() throws SQLException {
        Assertions.assertFalse(connectionManager.getConnection().isClosed());
    }
}
