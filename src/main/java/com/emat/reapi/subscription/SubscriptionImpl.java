package com.emat.reapi.subscription;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {
    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);
    private static final int MAX_ITEMS = 10;
    private static final int ERROR_LIMIT = 20;

    private final Faker faker;
    private final Subscriber<? super String> subscriber;

    private boolean isCancelled;
    private int counter = 0;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.faker = Faker.instance();
        this.subscriber = subscriber;
    }

    @Override
    public void request(long request) {
        if (isCancelled) {
            return;
        }
        log.info("Subscriber has requested {} items", request);
        if (request > ERROR_LIMIT) {
            log.error("Request validation error, max limit of requests is: {}, error limit is: {},  got: {} requests",
                    MAX_ITEMS,
                    ERROR_LIMIT,
                    request);
            subscriber.onError(new RuntimeException("Request validation error!!"));
            return;
        }
        for (long i = 0; i < request && counter < MAX_ITEMS; i++) {
            counter++;
            this.subscriber.onNext(this.faker.internet().emailAddress());
        }

        if (counter == MAX_ITEMS) {
            log.info("No more data to produce");
            this.subscriber.onComplete();
            this.isCancelled = true;
        }

    }

    @Override
    public void cancel() {
        log.info("Subscribed has cancelled");
        this.isCancelled = true;
    }
}
