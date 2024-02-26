package com.junitandmockito;

import java.util.Arrays;

//SUT
public class SomeBusinessService {

    //DEPENDENCY
    private SomeDataService someData;

	public SomeBusinessService(SomeDataService someData) {
		super();
		this.someData = someData;
	}

	public int calculateSum() {
		return Arrays.stream(someData.retrieveData())
				.reduce(Integer::sum).orElse(0);
	}

}
