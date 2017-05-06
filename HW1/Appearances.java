package assign1;

import java.util.*;
import java.util.Map.Entry;

public class Appearances {
	
	/**
	 * Returns the number of elements that appear the same number
	 * of times in both collections. Static method. (see handout).
	 * @return number of same-appearance elements
	 */
	public static <T> int sameCount(Collection<T> a, Collection<T> b) {
		HashMap<Integer, Integer> aHash=new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> bHash=new HashMap<Integer, Integer>();
		Iterator<T> it=a.iterator();
		while(it.hasNext()){
			int h=it.next().hashCode();
			if(aHash.containsKey(h))aHash.put(h, (int)aHash.get(h)+1);
			else aHash.put(h,1);
		}
		it=b.iterator();
		while(it.hasNext()){
			int h=it.next().hashCode();
			if(bHash.containsKey(h))bHash.put(h, (int)bHash.get(h)+1);
			else bHash.put(h,1);
		}
		int count=0;
		Iterator ite=aHash.entrySet().iterator();
		while(ite.hasNext()){
			Map.Entry en=(Map.Entry)ite.next();
			int k= (int) en.getKey();
			if(aHash.get(k).equals(bHash.get(k)))count++;
		}
		
		
		return count;
	}
	
}
