package oose.dea.casvansummeren.data;

import oose.dea.casvansummeren.exceptions.DatabaseConnectionFailedException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDatabaseService extends DatabaseProperties implements IDatabaseService{

    public MySqlDatabaseService(){
        super();
        loadDriver();
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
