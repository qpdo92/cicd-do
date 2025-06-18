package react;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/reactive")
public class ReactiveController {

    // Renvoie un seul élément de manière réactive
    @GetMapping("/mono")
    public Mono<String> getMono() {
        return Mono.just("Hello word")
                .delayElement(Duration.ofSeconds(1)); // Simulation d'un délai
    }

    // Renvoie une liste d'éléments sous forme de Flux
    @GetMapping("/flux")
    public Flux<String> getFlux() {
        return Flux.fromIterable(List.of("Élément 1", "Élément 2", "Élément 3"))
                .delayElements(Duration.ofSeconds(1)); // Simulation d'un flux avec un délai
    }
}
