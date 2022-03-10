package oose.dea.casvansummeren.data;

import javax.enterprise.inject.Default;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.Properties;

public abstract class DatabaseProperties {

    public DatabaseProperties(){
        loadProperties();
    }

    private static final String DATABASE_PROPERTIES_FILE = "database.properties";

    protected String connectionString;
    protected String user;
    protected String password;
    protected String driver;

    protected void loadProperties(){
        var properties = new Properties();
        try{
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        connectionString = properties.getProperty("connectionString");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
        driver = properties.getProperty("driver");
    }
}
