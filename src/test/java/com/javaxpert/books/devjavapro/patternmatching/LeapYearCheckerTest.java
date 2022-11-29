package com.javaxpert.books.devjavapro.patternmatching;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeapYearCheckerTest {
    private LeapYearChecker checker;
    @BeforeEach
    void setUp(){
        checker=new LeapYearChecker();
    }
    @Test
    void whenNon4DividableYearsShouldBeSeenAsNonLeapYears(){

        assertFalse(checker.isLeapYear(1901));
        assertFalse(checker.isLeapYear(1903));

    }

    @Test
    void whenSomeCenturiesAreNotLeap(){
        assertFalse(checker.isLeapYear(1900));
        assertFalse(checker.isLeapYear(1700));
    }

    @Test
    void someCenturiesAreLeapYears(){
        assertTrue(checker.isLeapYear(2000));
        assertTrue(checker.isLeapYear(1600));
    }

}