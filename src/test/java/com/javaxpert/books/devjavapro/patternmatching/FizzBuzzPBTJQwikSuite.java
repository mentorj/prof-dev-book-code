package com.javaxpert.books.devjavapro.patternmatching;

import net.jqwik.api.*;

/**
 * PBT with JQwik for the FizzBuzz problem
 */
public class FizzBuzzPBTJQwikSuite {
    private FizzBuzz  fizzbuzz = new FizzBuzz();
    @Property
    boolean every_third_element_starts_with_Fizz(@ForAll("divisibleBy3") int i) {
        return fizzbuzz.fizzbuzz().apply(i).startsWith("Fizz");
    }

    @Provide
    Arbitrary<Integer> divisibleBy3() {
        return Arbitraries.integers().between(1, 100000).filter(i -> i % 3 == 0);
    }
}
