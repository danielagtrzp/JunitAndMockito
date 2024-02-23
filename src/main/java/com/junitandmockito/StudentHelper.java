package com.junitandmockito;

public class StudentHelper {

    
	public static final int EXTRA_FOR_MATHS = 10;
	public static final int GRADE_B_UPPER_LIMIT = 80;
	public static final int GRADE_B_LOWER_LIMIT = 51;

	/*
	 * Grade B if marks are between 51 and 80 (both inclusive). Except for
	 * Maths where the upper limit is increased by 10.
	 */
	public boolean isGradeB(int marks, boolean isMaths) {

		int extraLimit = isMaths ? EXTRA_FOR_MATHS : 0;

		int upperLimit = GRADE_B_UPPER_LIMIT + extraLimit;

		return marks >= GRADE_B_LOWER_LIMIT && marks <= upperLimit;
	}
}
