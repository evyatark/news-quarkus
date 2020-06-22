package com.evyatark.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.reactivex.Flowable;

/**
 * A bean producing random prices every 5 seconds.
 * The prices are written to an internal channel("queue") named generated-price.
 * The configuration in the application.properties was changed so this channel is internal (not using AMQP Broker).
 */
@ApplicationScoped
public class PriceGenerator {

    private Random random = new Random();

    @Outgoing("generated-price")
    public Flowable<Integer> generate() {
        return Flowable.interval(5, TimeUnit.SECONDS)
                .map(tick -> {
                    System.out.println("" + tick);  // only for debugging
                    return random.nextInt(100);
                });
    }

}
