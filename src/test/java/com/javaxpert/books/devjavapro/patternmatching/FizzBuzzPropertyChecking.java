package com.javaxpert.books.devjavapro.patternmatching;

import io.vavr.test.Arbitrary;
import io.vavr.test.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * check the fizzbuzz using PBT framework
 * @author deadbrain
 */
public class FizzBuzzPropertyChecking {
    private FizzBuzz fizzbuzz;

    @BeforeEach
    void setUp(){
        fizzbuzz=new FizzBuzz();
    }
    @Test
    void ensureAllIntsDividableBy3HaveAfizzBuzzStartingWithWithFizz(){
        Arbitrary<Integer> allInts = Arbitrary.integer(); // <1>
        Property.def("fizzbuzz(int) starts with fizz")
                .forAll(allInts.filter(i-> i%3==0))
                .suchThat(i-> fizzbuzz.fizzbuzz().apply(i).startsWith("Fizz"))
                .check(100000,100000)
                .assertIsSatisfied();

    }

}
