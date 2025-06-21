package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootApplication
public class HelloWorldApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }
}

@RestController
class HelloWorldController {
    @GetMapping("/")
    public String helloWorld() {
        return "Hello World! du PC";

    }
}

@RestController
class WebhookController {
    @PostMapping("/github-webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody(required = false) String payload) {
        // Traitement du webhook
        System.out.println("Webhook reçu !");
        return ResponseEntity.ok("OK");
    }
}
