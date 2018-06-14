package com.javacode.examples.Btest;

public interface ITestSequence {
	public int getNonPeriodicCount(int loopStartIndex, int noOfBitCorrupted,
			int erorrBitPositionHolder[], int stringLength,
			int tsmtStringAsIntArray[]);

	public boolean isPeridic(String text);

	public int[] getErorrBitPositionHolder(int k);

	public int getNonPeriodicTotal(String s, int k);
	public int[] toIntArray(String s);
}
