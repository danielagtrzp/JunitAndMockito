package com.junitandmockito;

import java.math.BigDecimal;
import java.util.List;

import com.junitandmockito.others.Amount;
import com.junitandmockito.others.AmountImpl;
import com.junitandmockito.others.Currency;
import com.junitandmockito.others.CustomerBusinessService;
import com.junitandmockito.others.CustomerDataService;
import com.junitandmockito.others.DifferentCurrenciesException;
import com.junitandmockito.others.Product;

//STU
public class CustomerBusinessServiceImpl implements CustomerBusinessService {
	
	//DEPENDENCY
	private CustomerDataService customerDO;

	@Override
	public Amount calculateCustomerProductsSum(String id)
			throws DifferentCurrenciesException {
		
		List<Product> products = customerDO.retrieveCustomerProducts(id);

		if (products.size() == 0)
			return new AmountImpl(BigDecimal.ZERO, Currency.EURO);

		if(!doAllProductsHaveSameCurrency(products)) {
			throw new DifferentCurrenciesException();
		}

		return calculateSumOfProducts(products);
	}

	private Amount calculateSumOfProducts(List<Product> products) {
		
		Currency firstProductCurrency = products.get(0).getAmount()
				.getCurrency();

		BigDecimal sum = products.stream()
			.map(product -> product.getAmount().getValue())
			.reduce(BigDecimal.ZERO, BigDecimal::add);

		return new AmountImpl(sum, firstProductCurrency);
	}

	private boolean doAllProductsHaveSameCurrency(List<Product> products) throws DifferentCurrenciesException {

		Currency firstProductCurrency = products.get(0).getAmount()
				.getCurrency();

		return products.stream()
				.map(product -> product.getAmount().getCurrency())
				.allMatch(currency -> currency.equals(firstProductCurrency));
			
	}

}
