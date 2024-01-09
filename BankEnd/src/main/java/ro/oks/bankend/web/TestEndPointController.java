package ro.oks.bankend.web;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestEndPointController {

    @GetMapping("/api/test")
    public Map<String, String> test(Authentication authentication){
        return Map.of("message", "Bienvenue sur l'endpoint");
    }

}
