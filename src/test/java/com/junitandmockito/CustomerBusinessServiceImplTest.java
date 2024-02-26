package com.junitandmockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;
import com.junitandmockito.others.Currency;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.junitandmockito.others.Amount;
import com.junitandmockito.others.AmountImpl;
import com.junitandmockito.others.CustomerDataService;
import com.junitandmockito.others.DifferentCurrenciesException;
import com.junitandmockito.others.Product;
import com.junitandmockito.others.ProductImpl;
import com.junitandmockito.others.ProductType;


@ExtendWith(MockitoExtension.class)
public class CustomerBusinessServiceImplTest {

    @Mock
    CustomerDataService customerDataService;

    @InjectMocks
    CustomerBusinessServiceImpl customerBusinessServiceImpl;

    List<Product> products;

    @BeforeEach
    void setUp(){
        products = List.of();
    }


    @Test
    void testCalculateCustomerProductsSumEqualToCero() throws DifferentCurrenciesException {
        //given
        given(customerDataService.retrieveCustomerProducts(anyString())).willReturn(products);

        //when 
        Amount amount = customerBusinessServiceImpl.calculateCustomerProductsSum("10");

        //then "when comparing 2 Objects it is necesary to implement equals()"
        assertEquals(new AmountImpl(BigDecimal.ZERO, Currency.EURO), amount);
    }

    
    @Test
    void testCalculateCustomerProductsSumNotSameCurrency() throws DifferentCurrenciesException {
        //given
        products=Arrays.asList(
            new ProductImpl(1, "some", ProductType.KREDIT, new AmountImpl(new BigDecimal(10),Currency.EURO)),
            new ProductImpl(2, "somew", ProductType.BANK_GUARANTEE, new AmountImpl(new BigDecimal(10),Currency.INDIAN_RUPEE))
        );

        given(customerDataService.retrieveCustomerProducts(anyString())).willReturn(products);

        //when 

        //then 
        assertThrows(DifferentCurrenciesException.class, ()-> customerBusinessServiceImpl.calculateCustomerProductsSum("10"));
    }

    @Test
    void testCalculateCustomerProductsSum() throws DifferentCurrenciesException {
        //given
        products = Arrays.asList(
            new ProductImpl(1, "some", ProductType.KREDIT, new AmountImpl(new BigDecimal(10),Currency.EURO)),
            new ProductImpl(1, "somew", ProductType.BANK_GUARANTEE, new AmountImpl(new BigDecimal(10),Currency.EURO))
        );

        given(customerDataService.retrieveCustomerProducts(anyString())).willReturn(products);

        //when 
        Amount amount = customerBusinessServiceImpl.calculateCustomerProductsSum("1");
        //then 

        assertEquals(new AmountImpl(new BigDecimal(20), Currency.EURO), amount);
        
    }
}
