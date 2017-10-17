import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@EnableAutoConfiguration
public class HelloWorldController {

    @RequestMapping("/hello")
    @ResponseBody
    String helloWorld() {
        return getHelloWorld();
    }

    static String getHelloWorld() {
        return "Hello World";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloWorldController.class, args);
    }

}