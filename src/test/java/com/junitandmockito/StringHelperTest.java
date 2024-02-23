package com.junitandmockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void testReplaceAInFirst2Positions_null_exception() {
        //given
        inString = null;
        //then
        assertThrows(NullPointerException.class, ()->sHelper.replaceAInFirst2Positions(null));
    }
}
