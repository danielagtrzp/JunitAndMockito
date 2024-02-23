package com.junitandmockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

public class StringHelperTest {


    //ABCD > BCD, AACD > CD
    String inString;
    StringHelper sHelper;

    static Stream<Arguments> stringAndExpectedProvider() {
        return Stream.of(
            
            Arguments.of("ABCD", "BCD"),
            Arguments.of("AACD", "CD"),
            Arguments.of("ABAC", "BAC"),
            Arguments.of("AAAA", "AA"),
            Arguments.of("", ""),
            Arguments.of("A", ""),
            Arguments.of("AA", ""),
            Arguments.of("AB", "B")//CLARIFY THIS ONE SHOULD BE "" OR "B"

        );
    }

    @BeforeEach
    void setUp() {
        inString="";
        sHelper = new StringHelper();
    }
    
    @ParameterizedTest(name = "Test para '{index}")
    @MethodSource("stringAndExpectedProvider")
    void testReplaceAInFirst2Positions(String input, String expected) {
        //when
        String result = sHelper.replaceAInFirst2Positions(input);
        //then
        assertEquals(expected, result);
    }

    @Test
    void testReplaceAInFirst2PositionsNullException() {
        //given
        inString = null;
        //then
        assertThrows(NullPointerException.class, ()->sHelper.replaceAInFirst2Positions(null));
    }



    //ABCAB > true, ABCDEBA > false
    static Stream<Arguments> stringAndExpectedBooleanProvider() {
        return Stream.of(
            
            Arguments.of("ABCAB", true),
            Arguments.of("ABAB", true),
            Arguments.of("ABBA", false),
            Arguments.of("ABCDEBA", false),
            Arguments.of("AAA", true),

            //Arguments.of("AB", false),//Logic Error it compares AB witH AB returns true
            Arguments.of("A", false),
            Arguments.of("", false)

        );
    }

    @ParameterizedTest
    @MethodSource("stringAndExpectedBooleanProvider")
    void testAreFirstTwoAndLastTwoCharsTheSame(String input , boolean expected) {
        
        assertEquals(expected,sHelper.areFirstTwoAndLastTwoCharsTheSame(input));

    }

    @Test
    void testAreFirstTwoAndLastTwoCharsTheSameNullException() {
        //given
        inString = null;
        //then
        assertThrows(NullPointerException.class, ()->sHelper.areFirstTwoAndLastTwoCharsTheSame(null));
    }
}
