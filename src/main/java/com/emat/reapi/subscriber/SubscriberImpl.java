package com.emat.reapi.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriberImpl implements Subscriber<String> {
    private static final Logger log = LoggerFactory.getLogger(SubscriberImpl.class);
    private Subscription subscription;

    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(String email) {
        log.info("Received {}", email);

    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Something has happened: {}", throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("Task completed");

    }
}
