package com.junitandmockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.junitandmockito.others.SomeDataService;

@ExtendWith(MockitoExtension.class)
public class SomeBusinessServiceMockTest {

    @Mock
    SomeDataService someDataService;

    @InjectMocks
    SomeBusinessService someBusinessService;

    @Test
    void testCalculateSum() {
        given(someDataService.retrieveData()).willReturn(new int[] {10,20})
                                            .willReturn(new int[]{});

        assertEquals(30, someBusinessService.calculateSum());
        assertEquals(0, someBusinessService.calculateSum());

    }
}
