package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import assign1.StringCode;

public class StringCodeTest {

	@Test
	public void testMaxRun(){
		assertEquals(0,StringCode.maxRun(""));
		assertEquals(1,StringCode.maxRun(" "));
		assertEquals(2,StringCode.maxRun("aabshskaj"));
		assertEquals(4,StringCode.maxRun("aabbbrrnnnnq"));
		assertEquals(2,StringCode.maxRun("vgfthjgg"));
	}
	
	@Test
	public void testBlowup(){
		StringCode sc = new StringCode();
		assertEquals("attttxzzz",StringCode.blowup("a3tx2z"));
		assertEquals("2xxx",StringCode.blowup("12x"));
		assertEquals("",StringCode.blowup(""));
		assertEquals(" ",StringCode.blowup(" "));
		assertEquals("2222",StringCode.blowup("222"));
	}
	
	@Test
	public void testStringIntersect(){
		assertEquals(true,StringCode.stringIntersect("aaaa", "bbab", 1));
		assertEquals(true,StringCode.stringIntersect("mananakaigogoa", "rackaia", 3));
		assertEquals(false,StringCode.stringIntersect("kompilatorsgaachnia", "cislovecislife", 5));
		assertEquals(true,StringCode.stringIntersect("gubazavarsanikidze", "sadaagubaza?", 6));
		assertEquals(true,StringCode.stringIntersect("  ", " ", 1));
		assertEquals(false,StringCode.stringIntersect("", "bbab", 1));
	}
	
	
	
}
