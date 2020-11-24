package com.springboot.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

/**
 * Author: yutiy
 * Date: 2020/11/24 23:11
 * Email: 494657028@qq.com
 */
public class FluxTest {
    // just()：可以指定序列中包含的全部元素。创建出来的 Flux 序列在发布这些元素之后会自动结束。
    // fromArray()，fromIterable()和 fromStream()：可以从一个数组、Iterable 对象或 Stream 对象中创建 Flux 对象。
    // empty()：创建一个不包含任何元素，只发布结束消息的序列。
    // error(Throwable error)：创建一个只包含错误消息的序列。
    // never()：创建一个不包含任何消息通知的序列。
    // range(int start, int count)：创建包含从 start 起始的 count 个数量的 Integer 对象的序列。
    // interval(Duration period)和 interval(Duration delay, Duration period)：创建一个包含了从 0 开始递增的 Long 对象的序列。其中包含的元素按照指定的间隔来发布。除了间隔时间之外，还可以指定起始元素发布之前的延迟时间。
    public static void main(String[] args) throws InterruptedException {
        Flux.just("Hello", "World").subscribe(System.out::println);
        Flux.fromArray(new Integer[] {1, 2, 3}).subscribe(System.out::println);
        Flux.empty().subscribe(System.out::println);
        Flux.range(1, 4).subscribe(System.out::println);
        Flux.interval(Duration.of(1, ChronoUnit.SECONDS)).subscribe(System.out::println);

        // 线程延迟关闭，不然最后一个例子木有输出
        Thread.currentThread().join(10000);
    }

    // 通过同步和逐一的方式来产生 Flux 序列。序列的产生是通过调用所提供的 SynchronousSink 对象的 next()，complete()和 error(Throwable)方法来完成的
    public void generate() {
        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);

        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);
            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);
    }

    // 与 generate() 方法的不同之处在于所使用的是 FluxSink 对象。FluxSink 支持同步和异步的消息产生，并且可以在一次调用中产生多个元素
    public void create() {
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
    }

    public void other() throws InterruptedException {
        // filter: 对流中包含的元素进行过滤，只留下满足 Predicate 指定条件的元素：
        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);

        // take: 用来从当前流中提取元素
        // take(long n)：按照指定的数量来提取。
        // takeLast(long n)：提取流中的最后 N 个元素。
        // takeUntil(Predicate<? super T> predicate)：提取元素直到 Predicate 返回 true。
        // takeWhile(Predicate<? super T> continuePredicate)： 当 Predicate 返回 true 时才进行提取。
        Flux.range(1, 20).take(10).subscribe(System.out::println);
        Flux.range(1, 20).takeLast(10).subscribe(System.out::println);
        Flux.range(1, 20).takeWhile(i -> i < 10).subscribe(System.out::println);
        Flux.range(1, 20).takeUntil(i -> i == 10).subscribe(System.out::println);

        // reduce 和 reduceWith 操作符对流中包含的所有元素进行累积操作，得到一个包含计算结果的 Mono 序列。累积操作是通过一个 BiFunction 来表示的。在操作时可以指定一个初始值。如果没有初始值，则序列的第一个元素作为初始值。
        Flux.range(1, 10).reduce((x, y) -> x + y).subscribe(System.out::println);
        Flux.range(1, 10).reduceWith(() -> 10, (x, y) -> x + y).subscribe(System.out::println);

        // merge: 用来把多个流合并成一个 Flux 序列 -> 0 0 1 1
        Flux.merge(
                Flux.interval(Duration.of(500, ChronoUnit.MILLIS)).take(2),
                Flux.interval(Duration.of(500, ChronoUnit.MILLIS)).take(2)
        ).toStream().forEach(System.out::println);

        // buffer: 类似于数据流分区
        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);

        // 将两个流的元素安装元素位置一一组合, 没有配对上的被丢弃
        Flux.just("a", "b", "c", "d")
                .zipWith(Flux.just("e", "f", "g", "h", "i"))
                .subscribe(System.out::println);

        Flux.just("a", "b", "c", "d")
                .zipWith(Flux.just("e", "f", "g", "h", "i"), (s1, s2) -> String.format("%s-%s", s1, s2))
                .subscribe(System.out::println);

        // flatMap: 把流中的每个元素转换成一个流，再把所有流中的元素进行合并
        Flux.just(5, 10)
                .flatMap(x -> Flux.range(1, x).take(x))
                .subscribe(System.out::println);

        // 通过subscribe()方法处理正常和错误消息
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .subscribe(System.out::println, System.err::println);

        // 出现错误时返回默认值
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .onErrorReturn(0)
                .subscribe(System.out::println);

        // 出现错误时使用另外的流
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalArgumentException()))
                .onErrorResume(e -> {
                    if (e instanceof IllegalStateException) {
                        return Mono.just(0);
                    } else if (e instanceof IllegalArgumentException) {
                        return Mono.just(-1);
                    }
                    return Mono.empty();
                }).subscribe(System.out::println);

        Thread.currentThread().join(20000);
    }
}
