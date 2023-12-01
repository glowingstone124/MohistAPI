package mohist;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class Database {
    public static final String SQL_CONFIGURATION = "database.json";
    public static String jdbcUrl = getDatabaseInfo("url");
    public static String sqlusername = getDatabaseInfo("username");
    public static String sqlpassword = getDatabaseInfo("password");
    public static void init() {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, sqlusername, sqlpassword)) {
            connection.setAutoCommit(false);
            String init = "CREATE TABLE IF NOT EXISTS developers (\n" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    username VARCHAR(255) NOT NULL,\n" +
                    "    avatar VARCHAR(255),\n" +
                    "    github VARCHAR(255) NOT NULL,\n" +
                    "    description TEXT\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS clouds (\n" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    name VARCHAR(255) NOT NULL,\n" +
                    "    avatar VARCHAR(255),\n" +
                    "    description TEXT\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS tools (\n" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    name VARCHAR(255) NOT NULL,\n" +
                    "    avatar VARCHAR(255),\n" +
                    "    description TEXT\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS events (\n" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    name VARCHAR(255) NOT NULL,\n" +
                    "    avatar VARCHAR(255),\n" +
                    "    description TEXT,\n" +
                    "    url VARCHAR(255)\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS sources (\n" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY,\n" +
                    "    name VARCHAR(255) NOT NULL,\n" +
                    "    avatar VARCHAR(255),\n" +
                    "    description TEXT,\n" +
                    "    url VARCHAR(255),\n" +
                    "    version VARCHAR(10) NOT NULL\n" +
                    ");\n";
            try (PreparedStatement statement = connection.prepareStatement(init)) {
                statement.execute();
                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                ex.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getDatabaseInfo(String type) {
        JsonObject sqlObject = null;
        try {
            String jsonString = Files.readString(Path.of(SQL_CONFIGURATION));
            sqlObject = JsonParser.parseString(jsonString).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (sqlObject != null) {
            switch (type) {
                case "password":
                    return sqlObject.get("password").getAsString();
                case "username":
                    return sqlObject.get("username").getAsString();
                case "url":
                    return sqlObject.get("url").getAsString();
                default:
                    return null;
            }
        }
        return null;
    }
}
