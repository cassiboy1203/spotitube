package oose.dea.casvansummeren.data;

import oose.dea.casvansummeren.exceptions.DatabaseConnectionFailedException;

import javax.enterprise.inject.Default;
import javax.inject.Singleton;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Default
@Singleton
public class DatabaseService implements IDatabaseService{

    public DatabaseService(){
        loadProperties();
        loadDriver();
    }

    private static final String DATABASE_PROPERTIES_FILE = "database.properties";

    protected String connectionString;
    protected String user;
    protected String password;
    protected String driver;

    private void loadProperties(){
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

    private void loadDriver() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            //logger.log(Level.SEVERE, "Error loading driver " + dp.getDriver(), e);
        }
    }

    @Override
    public Connection connect() {
        try {
            return DriverManager.getConnection(connectionString, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseConnectionFailedException();
        }
    }
}
