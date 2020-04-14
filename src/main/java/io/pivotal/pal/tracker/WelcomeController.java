package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    String messageToPrint;

    public WelcomeController(@Value("${welcome.message}")
                                     String welcomeMessage) {
        this.messageToPrint = welcomeMessage;
    }

    @GetMapping("/")
    public String sayHello (){
        return messageToPrint;
    }
}
