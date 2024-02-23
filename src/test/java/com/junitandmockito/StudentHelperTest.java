package com.junitandmockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class StudentHelperTest {

    int grade;
    StudentHelper studentHelper;
    

    @BeforeEach
    void setUp() {
        grade = 0;
        studentHelper = new StudentHelper();
    }

    static Stream<Arguments> gradesAndExpectedBooleanProvider(){
        return Stream.of(
                        //grade|isMath|expected
            //is math
            Arguments.of(90,true,true),
            Arguments.of(51,true,true),
            Arguments.of(92,true,false),
            Arguments.of(50,true,false),
            Arguments.of(80,true,true),

            //is not math
            Arguments.of(80,false,true),
            Arguments.of(51,false,true),
            Arguments.of(50,false,false),
            Arguments.of(81,false,false),
            Arguments.of(70,false,true)
        );
    }

    @ParameterizedTest(name = "Test para '{index}")
   @MethodSource("gradesAndExpectedBooleanProvider")
    void testIsGradeB(int grade, boolean isMath,boolean expected) {
        //given 
    
        //when
        boolean result = studentHelper.isGradeB(grade, isMath);
        //then
        assertEquals(expected, result);
    }


}
