package ynn.util.collections.sql;

import java.util.Collection;

class InPredicate<E> implements Predicate<E> {
	
	private final Collection<E> collection;
	
	public InPredicate(Collection<E> collection) {
		if (collection == null) throw new IllegalArgumentException("collection cannot be null");
		this.collection = collection;
	}

	@Override
	public boolean satisfiedBy(E element) {
		for (E e : collection) {
			if (e == null) {
				if (element == null) return true;
			} else if (e.equals(element)) return true; 
		}
		return false;
	}

}
