package com.javaxpert.books.devjavapro.patternmatching.deconstruct;

import io.vavr.Tuple;
import io.vavr.Tuple4;
import io.vavr.Tuple5;
import io.vavr.match.annotation.*;

@Patterns  // <1>
/**
 * Helper class triggering class generation (DestructuringPatterens.java) in target/generated-src
 * if VAVR Pattern matching annotation processor is present on CLASSPATH
 * Provide here Unapply methods used to convert our business classes to Patterns usable in Match.of clauses
 * These classes enable what is called destructuring in Functional Programming
 * @author deadbrain
 */
class Destructure {
    @Unapply         // <2>
    static Tuple5<String,String,String,String,String> PostalAddress(PostalAddress addr){  //  <3>
        return Tuple.of(                                                // <4>
                addr.getCountryName(),
                addr.getTownName(),
                addr.getZipCode(),
                addr.getLineAddress1(),
                addr.getLineAddress2()
        );
    }

    @Unapply
    static Tuple4<String,String,String,String> ElectronicAddress(ElectronicAddress addr){
        return Tuple.of(
                addr.getEmailAddress(),
                addr.getFacebookNick(),
                addr.getInstaNick(),
                addr.getGsmNumber()
        );
    }
}


