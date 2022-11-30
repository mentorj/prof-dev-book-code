package com.javaxpert.books.devjavapro.patternmatching;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.Assume.*;

import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

/**
 * PBT testing with Junit-quickcheck
 */
@RunWith(JUnitQuickcheck.class)  // <1>
public class FizzBuzzPBTQuickcheck {
    private FizzBuzz fizzbuzz = new FizzBuzz();

    @Property(trials=100000
    )
    public void ensureDividableBy3IntsStartsWithFizz(Integer value){
        assumeThat( value, greaterThan(0));
        assumeThat( value%3,equalTo(0) );
        assertTrue(fizzbuzz.fizzbuzz().apply(value).startsWith("Fizz"));
    }


}
