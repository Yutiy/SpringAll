package com.springboot.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class ApplicationTests {

    Subscriber<Integer> subscriber;

    @BeforeEach
    public void before() {
        subscriber = new Subscriber<Integer>() {
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                System.out.println("接受到数据: " + item);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("处理完了!");
            }
        };
    }

    @Test
    public void test() {
        String[] strs = {"1", "2", "3"};

        // Mono表示0 ~ 1个元素的数据发布者，Flux表示 0 ~ N个元素的数据发布者
        Flux.fromArray(strs).map(Integer::parseInt).subscribe(subscriber);
        Mono.fromSupplier(() -> 1).map(s -> s + 1).subscribe(subscriber);
    }
}
