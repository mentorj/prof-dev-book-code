package com.javaxpert.books.devjavapro.benchmark;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * tests suite for fibo implementation
 */
public class ComputeFiboTest {

    @Test
    void fiboOneShouldReturnOne(){
        ComputeFibo fibo = new ComputeFibo();
        assertEquals(1,fibo.computeFibo(1) );
    }

    @Test
    void fiboMemoizedOneShouldReturnOne(){
        ComputeFibo fibo = new ComputeFibo();
        assertEquals(1,fibo.memoizedeFibo.apply(1));
    }

    @Test
    void checkFiboWith5(){
        ComputeFibo fibo = new ComputeFibo();
        assertEquals(5,fibo.computeFibo(5));
        assertEquals(5, fibo.memoizedeFibo.apply(5));
    }

}
