package mohist;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mohist")  // 设置基本的API路径

public class Controller {
    @GetMapping("/devs")
    public ResponseEntity<String> getDevs() {
        String message = Response.success(Lists.getDevs());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/clouds")
    public ResponseEntity<String> getClouds() {
        String message = Response.success(Lists.getClouds());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/tools")
    public ResponseEntity<String> getTools() {
        String message = Response.success(Lists.getTools());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/events")
    public ResponseEntity<String> getEvents() {
        String message = Response.success(Lists.getEvents());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/cores")
    public ResponseEntity<String> getCores(@RequestParam(name = "version", required = true) String version){
        JsonObject returnObj =new JsonObject();
        if (Lists.getCores(version) == null) {
            returnObj.addProperty("reason", "Requested version doesn't exist.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnObj.toString());
        }
        return ResponseEntity.status(HttpStatus.OK).body(Lists.getCores(version).toString());
    }
}
