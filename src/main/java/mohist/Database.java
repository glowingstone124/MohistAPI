package mohist;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Database {
    public static final String SQL_CONFIGURATION = "database.json";
    public static String jdbcUrl = getDatabaseInfo("url");
    public static String sqlusername = getDatabaseInfo("username");
    public static String sqlpassword = getDatabaseInfo("password");
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
