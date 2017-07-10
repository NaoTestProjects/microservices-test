package test.microservices.api.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Greeting {

    @CrossOrigin
    @RequestMapping("greet")
    public String greet() {
        return "Hello microservices";
    }
}
