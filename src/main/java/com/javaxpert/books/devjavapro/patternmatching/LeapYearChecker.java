package com.javaxpert.books.devjavapro.patternmatching;

import io.vavr.API;
import io.vavr.Function1;

import java.util.function.Predicate;

import static io.vavr.API.*;
import static io.vavr.API.Case;
import static io.vavr.Predicates.*;

/**
 *
 */
public class LeapYearChecker {

    private Predicate rule1 = (year) -> (int) year %4 ==0 ;
    private Predicate rule2 = (year) -> (int) year%100==0 && (int) year%400==0 ;

    public boolean isLeapYear(Integer targetYear){
        return Match( targetYear ).of(
                Case(  $(  allOf(rule1,rule2)) ,true ) ,
                Case( $(),false)
        );

    }
}
