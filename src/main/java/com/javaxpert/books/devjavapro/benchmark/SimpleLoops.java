package com.javaxpert.books.devjavapro.benchmark;

import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.collection.Stream;

/**
 *
 * A very small class comparing different ways to do the same job one using 2
 * loops and tests, one other with a single loop smartest solution is of course
 * to use the reduce based implementation
 *
 * @author deadbrain - jerome@javaxpert.com
 */
public class SimpleLoops {

    private List<Integer> valuesList;
    private Function1<Integer, Boolean> isEven = (n) -> n % 2 == 0;
    private Function1<Integer, Boolean> isOdd = (n) -> !isEven.apply(n);

    public SimpleLoops() {
        valuesList = Stream.from(1).take(200).toList();
    }

    public int computeWithReduce() {
        return valuesList.reduce((a, b) -> a + b).intValue();
    }

    /**
     * don't do that it is not an idiomatic code just for demo purpose
     **/
    public int computeWithSingleLoop() {
        int sum = 0;
        for (int i = 0; i < 200; i++) {
            sum += valuesList.get(i);
        }
        return sum;

    }

    /**
     * use 2 loops with filtering to compute the same result
     *
     */
    public int computeWith2Loops() {
        int sum = 0;
        for (int i = 0; i < 200; i++) {
            if (isEven.apply(i)) {
                sum += valuesList.get(i);
            }
        }

        for (int i = 0; i < 200; i++) {
            if (isOdd.apply(i)) {
                sum += valuesList.get(i);
            }
        }
        return sum;
    }
}
