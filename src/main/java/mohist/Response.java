package mohist;
import com.google.gson.JsonObject;
public class Response {
    public static String failed(String input) {
        JsonObject returnObject = new JsonObject();
        returnObject.addProperty("code", -1);
        returnObject.addProperty("output", input);
        return returnObject.toString();
    }

    public static String success(String input) {
        JsonObject returnObject = new JsonObject();
        returnObject.addProperty("code", 0);
        returnObject.addProperty("output", input);
        return returnObject.toString();
    }

    public static String denied(String input) {
        JsonObject returnObject = new JsonObject();
        returnObject.addProperty("code", 1);
        returnObject.addProperty("output", input);
        return returnObject.toString();
    }
}
