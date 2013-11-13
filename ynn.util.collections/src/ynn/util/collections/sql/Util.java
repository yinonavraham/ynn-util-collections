package ynn.util.collections.sql;

import java.util.Collection;
import java.util.Iterator;

final class Util {
	
	private Util() { }
	
	/**
	 * Remove from the given collection elements which do not satisfy the given predicate 
	 * @param collection - the collection to remove elements from
	 * @param predicate the predicate to use
	 */
	public static <E> void removeUnsatisfyingElements(Collection<E> collection, Predicate<E> predicate) {
		Iterator<E> iterator = collection.iterator();
		while (iterator.hasNext()) {
			E element = iterator.next();
			if (!predicate.satisfiedBy(element)) {
				iterator.remove();
			}
		}
	}

}
