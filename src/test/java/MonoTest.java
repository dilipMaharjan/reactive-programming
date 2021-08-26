import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoTest {

    @Test
    public void firstMono() {
        Mono.just("A");
    }

    @Test
    public void monoWithConsumer() {
        Mono.just("A").log().subscribe(
                c -> System.out.println(c)
        );
    }

    @Test
    public void monoWithDoOn() {
        Mono.just("A").log()
                .doOnSubscribe(subscription -> System.out.println("Subscription : " + subscription))
                .doOnRequest(req -> System.out.println("Request : " + req))
                .doOnNext(s -> System.out.println("Next : " + s))
                .doOnSuccess(success -> System.out.println("OnSuccess : " + success))
                .subscribe(System.out::println);
    }

    @Test
    public void monoWithEmpty() {
        Mono.empty().log().subscribe(System.out::println, null, () -> System.out.println("Done"));
    }

    @Test
    public void monoWithError() {
        Mono.error(new RuntimeException()).log().subscribe();
    }

    @Test
    public void monoWithErrorCatch() {
        Mono.error(new Exception()).log().subscribe(
                System.out::println,
                e -> System.out.println("The error : " + e)
        );
    }
}
