package com.javatest.example.Btest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.javacode.examples.Btest.ITestSequence;
import com.javacode.examples.Btest.TestSequence;

public class TestSequenceTest {
	private static ITestSequence itTestSequence;
//Bibhuti pradhan commit1
	@BeforeClass
	public static void initCalculator() {
		itTestSequence = new TestSequence();
	}

	@Before
	public void beforeEachTest() {

	}

	@After
	public void afterEachTest() {
		System.out.println("This is exceuted after each Test");
	}

	@Test
	public void testIsPeridic() {
		Assert.assertTrue(itTestSequence.isPeridic("101010"));
		Assert.assertTrue(itTestSequence.isPeridic("10001000"));
		Assert.assertTrue(itTestSequence.isPeridic("11101110"));
		Assert.assertFalse(itTestSequence.isPeridic("1010101"));
		Assert.assertFalse(itTestSequence.isPeridic("100010001"));
		Assert.assertFalse(itTestSequence.isPeridic("111011101"));
	}
	/*
	 * provide the transmitted string and no of bits corrupted
	 */
	@Test
	public void testGetNonPeriodicTotal() {
		Assert.assertEquals(0,itTestSequence.getNonPeriodicTotal("00000", 0));
		Assert.assertEquals(3,itTestSequence.getNonPeriodicTotal("101", 1));
		Assert.assertEquals(6,itTestSequence.getNonPeriodicTotal("101", 3));
		Assert.assertEquals(175,itTestSequence.getNonPeriodicTotal("1010101010", 3));
		Assert.assertEquals(620,itTestSequence.getNonPeriodicTotal("1111111100", 5));
		Assert.assertEquals(88,itTestSequence.getNonPeriodicTotal("10101010", 3));
		Assert.assertEquals(8,itTestSequence.getNonPeriodicTotal("01010101", 1));
	}
	@Test
	public void testGetErorrBitPositionHolder(){
		Assert.assertArrayEquals(new int[]{-1, -1,-1,-1,-1},itTestSequence.getErorrBitPositionHolder(5));
		Assert.assertArrayEquals(new int[]{-1, -1,-1,-1},itTestSequence.getErorrBitPositionHolder(4));
		Assert.assertArrayEquals(new int[]{},itTestSequence.getErorrBitPositionHolder(0));
	}
	@Test
	public void testToIntArray(){
	Assert.assertArrayEquals(new int[]{0, 0,0,0,0},itTestSequence.toIntArray("00000"));
	Assert.assertArrayEquals(new int[]{0, 0,1},itTestSequence.toIntArray("001"));
	Assert.assertArrayEquals(new int[]{1,0,0,1},itTestSequence.toIntArray("1001"));
	}
}
