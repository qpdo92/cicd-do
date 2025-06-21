package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    app.post("/github-webhook",(req,res)=>

    {
  // traitement du webhook
  res.status(200).send("OK");
});

}