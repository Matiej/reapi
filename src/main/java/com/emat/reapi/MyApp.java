package com.emat.reapi;

import com.emat.reapi.publisher.MyPublisherImpl;
import com.emat.reapi.subscriber.SubscriberImpl;

import java.time.Duration;

public class MyApp {

    public static void main(String[] args) throws InterruptedException {

//            demo2();
        demoTriggersError();

    }

    private static void demo1() throws InterruptedException {
        var publisher = new MyPublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3L);
        Thread.sleep(Duration.ofSeconds(1));
        subscriber.getSubscription().request(3L);
        Thread.sleep(Duration.ofSeconds(1));
        subscriber.getSubscription().request(3L);
        subscriber.getSubscription().request(3L);
    }

    private static void demo2() throws InterruptedException {
        var publisher = new MyPublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3L);
        Thread.sleep(Duration.ofSeconds(1));
        subscriber.getSubscription().request(3L);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(3L);
        subscriber.getSubscription().request(3L);
    }

    private static void demoTriggersError() throws InterruptedException {
        var publisher = new MyPublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3L);
        Thread.sleep(Duration.ofSeconds(1));
        subscriber.getSubscription().request(3L);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(32L);
        Thread.sleep(Duration.ofSeconds(1));
        subscriber.getSubscription().request(3L);
    }
}
