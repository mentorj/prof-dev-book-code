package com.javaxpert.books.devjavapro.patternmatching;


import io.vavr.Function1;
import io.vavr.Tuple;
import static io.vavr.API.*;
import static io.vavr.API.Case;
import static io.vavr.collection.Stream.*;
import static io.vavr.Patterns.*;

/**
 * Fizzbuzz implementation using VAVR pattern matching
 * @author deadbrain ,jerome@javaxpert.com
 */
public class FizzBuzz {

    public Function1<Integer,String> fizzbuzz(){
        return (value) -> Match( Tuple.of(value % 3 == 0 , value % 5 == 0) ).of(
                Case( $Tuple2 ( $(true),$(true) ), "FizzBuzz")  ,
                Case( $Tuple2( $(true) ,$() ),"Fizz"),
                Case( $Tuple2( $(),$(true) ),"Buzz"),
                Case( $() ,value.toString() )
        );

    }

    public String generateFizzBuzzString(int howMuch){
        return from(1)
                .map(integer -> fizzbuzz().apply(integer))
                .take(howMuch)
                .reduce((s, s2) -> s+s2);
    }

    public static void main(String[] args) {
        FizzBuzz fizz = new FizzBuzz();
        String result = fizz.generateFizzBuzzString(20);
        System.out.printf("Result is "+ result);
    }
}
