package mohist;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import static mohist.Database.*;

public class Lists {
    public static JsonObject getDevs() {
        JsonObject returnObj = new JsonObject();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, sqlusername, sqlpassword)) {
            String query = "SELECT * FROM developers";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    JsonObject devObj = new JsonObject();
                    devObj.addProperty("github", resultSet.getString("github"));
                    devObj.addProperty("avatar", resultSet.getString("avatar"));
                    devObj.addProperty("description", resultSet.getString("description"));
                    String developerName = resultSet.getString("name");
                    if (returnObj.has(developerName)) {
                        JsonArray existingArray = returnObj.getAsJsonArray(developerName);
                        existingArray.add(devObj);
                    } else {
                        JsonArray newArray = new JsonArray();
                        newArray.add(devObj);
                        returnObj.add(developerName, newArray);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnObj;
    }
    public static JsonObject getClouds() {
        JsonObject returnObj = new JsonObject();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, sqlusername, sqlpassword)) {
            String query = "SELECT * FROM clouds";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    JsonObject cloudObj = new JsonObject();
                    cloudObj.addProperty("avatar", resultSet.getString("avatar"));
                    cloudObj.addProperty("description", resultSet.getString("description"));
                    String cloudName = resultSet.getString("name");
                    if (returnObj.has(cloudName)) {
                        JsonArray existingArray = returnObj.getAsJsonArray(cloudName);
                        existingArray.add(cloudObj);
                    } else {
                        JsonArray newArray = new JsonArray();
                        newArray.add(cloudObj);
                        returnObj.add(cloudName, newArray);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnObj;
    }
    public static JsonObject getTools() {
        JsonObject returnObj = new JsonObject();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, sqlusername, sqlpassword)) {
            String query = "SELECT * FROM tools";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                getListResult(returnObj, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnObj;
    }
    public static JsonObject getEvents(){
        JsonObject returnObj = new JsonObject();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, sqlusername, sqlpassword)) {
            String query = "SELECT * FROM events";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    JsonObject EventsObj = new JsonObject();
                    EventsObj.addProperty("avatar", resultSet.getString("avatar"));
                    EventsObj.addProperty("description", resultSet.getString("description"));
                    EventsObj.addProperty("url", resultSet.getString("url"));
                    String EventsName = resultSet.getString("name");
                    if (returnObj.has(EventsName)) {
                        JsonArray existingArray = returnObj.getAsJsonArray(EventsName);
                        existingArray.add(EventsObj);
                    } else {
                        JsonArray newArray = new JsonArray();
                        newArray.add(EventsObj);
                        returnObj.add(EventsName, newArray);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnObj;
    }
    public static JsonObject getCores(String version){
        ArrayList<String> coresVer= new ArrayList<>(Arrays.asList("1.12.2", "1.16.5","1.18.2","1.19.4","1.20.1","1.20.2"));
        if (!coresVer.contains(version)){
            return null;
        }
        JsonObject returnObj = new JsonObject();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, sqlusername, sqlpassword)) {
            String query = "SELECT * FROM sources WHERE version = ?";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                statement.setString(1, version);
                getListResult(returnObj, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnObj;
    }

    private static void getListResult(JsonObject returnObj, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            JsonObject coresObj = new JsonObject();
            coresObj.addProperty("avatar", resultSet.getString("avatar"));
            coresObj.addProperty("description", resultSet.getString("description"));
            coresObj.addProperty("url", resultSet.getString("url"));
            String coresName = resultSet.getString("name");
            if (returnObj.has(coresName)) {
                JsonArray existingArray = returnObj.getAsJsonArray(coresName);
                existingArray.add(coresObj);
            } else {
                JsonArray newArray = new JsonArray();
                newArray.add(coresObj);
                returnObj.add(coresName, newArray);
            }
        }
    }
}
