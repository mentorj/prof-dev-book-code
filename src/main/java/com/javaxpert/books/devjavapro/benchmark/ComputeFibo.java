package com.javaxpert.books.devjavapro.benchmark;

import io.vavr.Function1;

import static io.vavr.API.*;
import static io.vavr.Predicates.*;

import java.math.BigInteger;

/**
 * Compute fibonacci sequence using recursion technique
 * and adds another improved version with dynamic programmming and memoization
 * @author deadbrain
 */
public class ComputeFibo {
    public Integer computeFibo(Integer n){

        return Match(n).of(
                Case($(isIn( 1,2 ))  ,1 ),
                Case($(),o -> computeFibo(n-1)+ computeFibo(n-2))
        );
    }

    public int computeFiboMemoized(Integer n){
        Function1<Integer,Integer> target = Function1.of(this::computeFibo);
        Function1<Integer,Integer> memoized = target.memoized();
        return memoized.apply(n);
    }

}
