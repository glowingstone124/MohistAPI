package mohist;
import com.google.gson.JsonObject;
public class Response {
    public static String failed(JsonObject input) {
        input.addProperty("code", -1);
        return input.toString();
    }

    public static String success(JsonObject input) {
        input.addProperty("code", 0);
        return input.toString();
    }

    public static String denied(JsonObject input) {
        input.addProperty("code", 1);
        return input.toString();
    }
}
