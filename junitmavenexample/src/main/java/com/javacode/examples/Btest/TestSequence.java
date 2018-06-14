package com.javacode.examples.Btest;

import java.util.Scanner;

public class TestSequence implements ITestSequence {

	// public static long count;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		int testCastCount = scan.nextInt();
		long result[] = new long[testCastCount];
		int r = 0;
		while (testCastCount-- > 0) {
			int n = scan.nextInt(); // let transmitted string is: "101" then n=3
			int k = scan
					.nextInt(); /*
								 * let k=3, means max kth no bits corrupted. so
								 * in this case , the corrupted position can be
								 * 1,2,3 ,1 2,1 3,2 3,1 2 3 
								 * if 
								 * corrupted position 1 ,then string will be 001   not peroidic .
								 * corrupted position 2 ,then string will be 111       peroidic 
								 * corrupted position 3 ,then string will be 100    not peroidic
								 * 
								 * corrupted position 1 & 2 ,then string will be  011   not peroidic 
								 * corrupted position 1 &3,then string will be    000       peroidic
								 *  corrupted position 2 & 3 ,then string will be  110  not peroidic
								 * corrupted position 1,2,3 ,then string will be   010  not peroidic
								 * if not corrupted(ie k =0) then string   will be 101    not peroidic
								 * 
								 * 
								 * so ,here noOfnonperiodic count will be :6
								 * 
								 */

			String transmittedString = scan.next();
			int nonPeriodicCount=new TestSequence().getNonPeriodicTotal(transmittedString, k);
			// System.out.println(count);
			result[r] = nonPeriodicCount;
			// count = 0;
			r++;
		}
		scan.close();

		for (int i = 0; i < result.length; i++)
			System.out.println(result[i]);

	}

	/*
	 * this method will give a copy of actual transmitted string. so the
	 * corrupted position will be changed on copied string
	 * 
	 */
	public int[] getClone(int tsmtStringAsIntArray[]) {
		int copyArray[] = new int[tsmtStringAsIntArray.length];
		for (int i = 0; i < tsmtStringAsIntArray.length; i++)
			copyArray[i] = tsmtStringAsIntArray[i];

		return copyArray;
	}

	/*
	 * getNonPeriodicCount(------) return count of nonperiodic string by
	 * following steps step 1-method will generate all the corrupted position
	 * upto max kth length. step 2- Each time method called it will generate a
	 * corrupted position . step 3 -Now corrupted position is changed from 1-->0
	 * or 0--->1 . to get possible string sent step 4-after changing the positon
	 * , changed string will be checked if it is periodic or not. step 3,4
	 * should be on copy of string else we will loss the actual string
	 * 
	 * parameters: -------- loopStartIndex: this is intial staring point of loop
	 * let 3 bit is corrupted of string "101" . so the corrupted positon will be
	 * 1,2,3 ,1 2,1 3,2 3,1 2 3 to find out this position we can write , for(int
	 * i=0;i<3;i++) for(int j=i+1;j<3;j++) for(int k=j+1,k<3;k++)
	 * 
	 * Above code is implemented here using recursion
	 * 
	 * noOfBitCorrupted: it will be up to kth bit
	 * 
	 * 
	 */
	public int getNonPeriodicCount(int loopStartIndex, int noOfBitCorrupted, int erorrBitPositionHolder[],
			int stringLength, int tsmtStringAsIntArray[]) {
		int nonPeriodicCount = 0;
		if (noOfBitCorrupted != 0) {
			// String s = "";
			/*
			 * Follwing code copy the string , then change the corrupted
			 * position
			 * 
			 */
			int copy[] = getClone(tsmtStringAsIntArray);

			for (int i = 0; i < erorrBitPositionHolder.length; i++) {
				if (erorrBitPositionHolder[i] != -1) {
					// change the corrupted position from 1-->0 or 0--->1 on
					// copied array
					copy[erorrBitPositionHolder[i]] = 1 - copy[erorrBitPositionHolder[i]];

				}
			}
			String collectIntArryAasString = "";
			for (int j = 0; j < copy.length; j++)
				collectIntArryAasString += copy[j];

			if (!isPeridic(collectIntArryAasString.trim()))
				nonPeriodicCount++;
			nonPeriodicCount %= 1000000007;

		} else {
			String collectIntArryAasString = "";
			for (int j = 0; j < tsmtStringAsIntArray.length; j++)
				collectIntArryAasString += tsmtStringAsIntArray[j];
			if (!isPeridic(collectIntArryAasString.trim()))
				nonPeriodicCount++;
			nonPeriodicCount %= 1000000007;

		}

		if (noOfBitCorrupted == erorrBitPositionHolder.length)
			return nonPeriodicCount;
		/*
		 * follwing loop will generate the combination of 1 to n of length k ex:
		 * if n=3, k=2 , combination will be 1,2,3,1 2,2 3,1 3, all this
		 * combination will be used as the possible corrupted position of the
		 * transmitted string
		 */
		for (int i = loopStartIndex; i < stringLength; i++) {

			erorrBitPositionHolder[noOfBitCorrupted] = i;
			nonPeriodicCount += getNonPeriodicCount(i + 1, noOfBitCorrupted + 1, erorrBitPositionHolder, stringLength,
					tsmtStringAsIntArray);
			nonPeriodicCount %= 1000000007;
			erorrBitPositionHolder[noOfBitCorrupted] = -1;

		}

		return nonPeriodicCount;
	}

	/*
	 * 
	 * check stirng is periodic or not
	 */
	public boolean isPeridic(String text) {

		return text.matches("(\\d+?)(\\1+?)");
	}
	
public int[]	getErorrBitPositionHolder(int k){
	int erorrBitPositionHolder[]=new int[k];
		for (int i = 0; i < k; i++) 
			erorrBitPositionHolder[i] = -1;
		return erorrBitPositionHolder;
	}

public int getNonPeriodicTotal(String s,int k){
	int[] intArray=toIntArray(s);
	// erorrBitPositionHolder will hold the possible corrupted position  of the transmitted string which will be up to max Kth bit
	int[] errorBitPositionHolder=getErorrBitPositionHolder(k);
	return getNonPeriodicCount(0, 0, errorBitPositionHolder, s.length(), intArray);
}
public int[] toIntArray(String s){
	char charArray[] = s.toCharArray();

	// int transmittedStringAsIntArray[] will hold transmitted string as
	// int array, in this case {1,0,1}
	int intArray[] = new int[charArray.length];
for (int i = 0; i < intArray.length; i++)
		intArray[i] = Integer.parseInt(String.valueOf(charArray[i]));

return intArray;
}
}