package com.maciej.app;

import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

public class DatabaseConnector {
    private final Logger logger = Logger.getLogger(DatabaseConnector.class.getName());

    public void handleEvents(List<Event> events) {
        try (Connection con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/xdb", "SA", "")) {
            if (con != null) {
                logger.info("Connection created successfully");
                DatabaseMetaData dbm = con.getMetaData();
                ResultSet res = dbm.getTables(null, null, "EVENTS", null);
                Statement statement = con.createStatement();

                //statement.executeUpdate("DROP TABLE events");

                if (!res.next()) createTable(statement);
                insertEvents(statement, events);
            }
            else logger.info("Problem with creating connection to database");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE events (" +
                "id VARCHAR(100) NOT NULL," +
                "duration INT NOT NULL," +
                "type VARCHAR(100) DEFAULT NULL," +
                "host VARCHAR(100) DEFAULT NULL," +
                "alert BOOLEAN," +
                "PRIMARY KEY (id))");
        logger.info("Table created successfully");
    }

    private void insertEvents(Statement statement, List<Event> events) throws SQLException {
        for (Event event : events) {
            insertEvent(statement, event);
        }
        logger.info("Elements inserted to database");
    }

    private void insertEvent(Statement statement, Event event) throws SQLException {
        statement.executeUpdate("INSERT INTO events VALUES ("
                + "'" + event.getId()+"' , "
                + event.getDuration()+", "
                + "'" + event.getType()+"' , "
                + "'" + event.getHost()+"' , "
                + event.isAlert()+")");
    }
}
