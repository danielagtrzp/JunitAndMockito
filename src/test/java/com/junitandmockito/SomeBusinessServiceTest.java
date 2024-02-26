package com.junitandmockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.junitandmockito.others.SomeDataService;

public class SomeBusinessServiceTest {

    
    SomeBusinessService someBusinessService;
    SomeDataService someDataService;

    @BeforeEach
    void setUp() {
        someDataService = new SomeDataService();
        someBusinessService = new SomeBusinessService(someDataService);
    }

    @Test
    void testCalculateSum() {
        //when
        int result = someBusinessService.calculateSum();
        assertEquals(60, result);
    }
}
