package assign1;

import java.util.HashSet;
import java.util.Set;

// CS108 HW1 -- String static methods

public class StringCode {

	/**
	 * Given a string, returns the length of the largest run.
	 * A a run is a series of adajcent chars that are the same.
	 * @param str
	 * @return max run length
	 */
	public static int maxRun(String str) {
		if(str.length()==0)return 0;
		int count=0;
		int max=0;
		char a=str.charAt(0);
		char b=str.charAt(0);
		for(int i=0;i<str.length();i++){
			a=str.charAt(i);
			if(a==b)count++;
			else count=1;
			if(count>max)max=count;
			b=a;
		}
		return max;
	}

	
	/**
	 * Given a string, for each digit in the original string,
	 * replaces the digit with that many occurrences of the character
	 * following. So the string "a3tx2z" yields "attttxzzz".
	 * @param str
	 * @return blown up string
	 */
	public static String blowup(String str) {
		if(str.length()==0)return "";
		String res="";
		if(Character.isDigit(str.charAt(0))){
			int n = Character.getNumericValue(str.charAt(0));
			if(str.length()>1){
				for(int i=0; i<n;i++){
					res+=str.charAt(1);
				}
			}
		}else res=Character.toString(str.charAt(0));
		return res+blowup(str.substring(1));
	}
	
	/**
	 * Given 2 strings, consider all the substrings within them
	 * of length len. Returns true if there are any such substrings
	 * which appear in both strings.
	 * Compute this in linear time using a HashSet. Len will be 1 or more.
	 */
	public static boolean stringIntersect(String a, String b, int len) {
		HashSet<String> hash=new HashSet<String>();
		for(int i=0;i<=a.length()-len;i++)hash.add(a.substring(i, i+len));
		for(int i=0;i<=b.length()-len;i++)if(hash.contains(b.substring(i, i+len)))return true;
		return false;
	}
}
