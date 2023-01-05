package com.javaxpert.books.devjavapro.resilience;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.timelimiter.TimeLimiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * tests suite for resilience related features
 * @author deadbrain,jerome@javaxpert.com
 */
public class ResilienceTestsSuite {

    @Test
    void circuitBreakerShouldOpen(){
        Supplier<String> supplier = ()-> {
            int randomValue = new Random().nextInt(100);
            if(randomValue>10){
                throw new RuntimeException("Bad value");
            }
            return ""+randomValue;
        };
        CircuitBreakerConfig cfg = CircuitBreakerConfig.custom()
                .failureRateThreshold(20f)
                        .build();
        CircuitBreaker cb = CircuitBreaker.of("myCB",cfg);
        Decorators.DecorateSupplier<String> decorateSupplier =
                Decorators.ofSupplier(supplier)
                        .withCircuitBreaker(cb).
                        withFallback(throwable -> "Fallback invoked");

        for(int i =0; i<100;i++){
            decorateSupplier.get();
        }
        Assertions.assertTrue(cb.getState()== CircuitBreaker.State.OPEN);
    }


    @Test()
    void retryShouldBeActivatedWhenCallFails(){
        Supplier<String> supplier = ()-> {
            System.out.println("Supplier invoked");
            throw new RuntimeException("Not implemented");
        };
        RetryConfig cfg = RetryConfig.custom()
                .retryExceptions(RuntimeException.class)
                .failAfterMaxAttempts(true)
                .maxAttempts(10)
 //               .writableStackTraceEnabled(true)
                .build();

        Retry retry = Retry.of("retru",cfg);
        Decorators.DecorateSupplier<String> decorateSupplier = Decorators.ofSupplier(supplier)
                .withRetry(retry);
        Assertions.assertThrows( RuntimeException.class, ()  -> decorateSupplier.get() );

    }


    @Test
    void actionShouldCompleteAfterNAttempts(){
        AtomicInteger  counter = new AtomicInteger(0);
        Supplier<String> supplier = ()-> {
            System.out.println("Counting Supplier invoked");
            if(counter.get()>4)
                return "42";
            counter.incrementAndGet();
            throw new RuntimeException("Counter os over");
        };
        RetryConfig cfg = RetryConfig.custom()
                .retryExceptions(RuntimeException.class)
                .failAfterMaxAttempts(true)
                .maxAttempts(10)
                .build();

        Retry retry = Retry.of("custom",cfg);
        Decorators.DecorateSupplier<String> decorateSupplier = Decorators.ofSupplier(supplier).withRetry(retry);
        String callResult  = decorateSupplier.get();
        Assertions.assertTrue(retry.getMetrics().getNumberOfSuccessfulCallsWithRetryAttempt()==1);
        Assertions.assertEquals("42",callResult);
    }

    @Test
    void fallbackShouldBeActivatedAfterMaxRetries(){
        Supplier<String> supplier = ()-> {
            System.out.println("Counting Supplier 2 invoked");
            throw new RuntimeException("Counter os over");
        };

        RetryConfig cfg = RetryConfig.custom()
                .retryExceptions(RuntimeException.class)
                .failAfterMaxAttempts(true)
                .maxAttempts(10)
                .build();

        Retry retry = Retry.of("custom",cfg);
        Decorators.DecorateSupplier<String> decorateSupplier =
                Decorators.ofSupplier(supplier)
                        .withRetry(retry)
                        .withFallback( (s, throwable) -> "Fallback fired")
                ;
        String result = decorateSupplier.get();
        Assertions.assertTrue(result.startsWith("Fallback"));

    }
}
