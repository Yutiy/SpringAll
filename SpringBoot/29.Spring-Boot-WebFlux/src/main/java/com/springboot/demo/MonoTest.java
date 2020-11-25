package com.springboot.demo;

import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Author: yutiy
 * Date: 2020/11/24 23:22
 * Email: 494657028@qq.com
 */
public class MonoTest {
    // Mono 类中也包含了一些与 Flux 类中相同的静态方法。这些方法包括 just()，empty()，error()和 never()等。除了这些方法之外，Mono 还有一些独有的静态方法:

    // fromCallable()、fromCompletionStage()、fromFuture()、fromRunnable()和 fromSupplier()：分别从 Callable、CompletionStage、CompletableFuture、Runnable 和 Supplier 中创建 Mono。
    // delay(Duration duration)：创建一个 Mono 序列，在指定的延迟时间之后，产生数字 0 作为唯一值。
    // ignoreElements(Publisher<T> source)：创建一个 Mono 序列，忽略作为源的 Publisher 中的所有元素，只产生结束消息。
    // justOrEmpty(Optional<? extends T> data)和 justOrEmpty(T data)：从一个 Optional 对象或可能为 null 的对象中创建 Mono。只有 Optional 对象中包含值或对象不为 null 时，Mono 序列才产生对应的元素。
    public static void main(String[] args) {
        Mono.just("are").subscribe(System.out::println);
        Mono.empty().subscribe(System.out::println);
        Mono.fromSupplier(() -> "you").subscribe(System.out::println);
        Mono.justOrEmpty(Optional.of("ok")).subscribe(System.out::println);

        Mono.create(sink -> sink.success("Hello")).subscribe(System.out::println);
    }
}
