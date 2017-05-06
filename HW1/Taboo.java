/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/
package assign1;

import java.util.*;

public class Taboo<T> {
	private List<T> rules;
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */
	public Taboo(List<T> rules) {
		this.rules=rules;
	}
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param elem
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T elem) {
		Set<T> s=new HashSet<T>();
		Iterator it=rules.iterator();
		while(it.hasNext()){
			T t=(T)it.next();
			if(t==null)continue;
			if(t.equals(elem)&&it.hasNext()){
				T e=(T)it.next();
				if(e==null)continue;
				s.add(e);
			}
		}
		return s;
		}
	
	/**
	 * Removes elements from the given list that
	 * violate the rules (see handout).
	 * @param list collection to reduce
	 */
	public void reduce(List<T> list) {
		Iterator it=list.iterator();
		int idx=1;
		for(int i=0;i<list.size();i++){
			T t=list.get(i);
			Set<T> s=noFollow(t);
			if(i+1<list.size()&&s.contains(list.get(i+1))){
				list.remove(i+1);
				i--;
			}
		}
	}
}
