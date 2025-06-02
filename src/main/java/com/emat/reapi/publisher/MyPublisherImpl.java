package com.emat.reapi.publisher;

import com.emat.reapi.subscription.SubscriptionImpl;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class MyPublisherImpl implements Publisher<String> {

    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        var subscription = new SubscriptionImpl(subscriber);
        subscriber.onSubscribe(subscription);
    }
}
