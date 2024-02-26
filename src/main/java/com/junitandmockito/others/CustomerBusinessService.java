package com.junitandmockito.others;

public interface CustomerBusinessService {

	Amount calculateCustomerProductsSum(String id)
			throws DifferentCurrenciesException;

}
