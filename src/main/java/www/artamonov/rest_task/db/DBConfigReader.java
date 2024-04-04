package www.artamonov.rest_task.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfigReader {
    private static final Properties properties = new Properties();

    private DBConfigReader() {}

    static {
        try (InputStream input = DBConfigReader.class.getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUrl() {
        return properties.getProperty("db.url");
    }

    public static String getUsername() {
        return properties.getProperty("db.user");
    }

    public static String getPassword() {
        return properties.getProperty("db.password");
    }
}
