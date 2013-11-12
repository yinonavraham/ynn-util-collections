package ynn.util.collections.sql;

import java.util.Collection;
import java.util.Iterator;

final class Util {
	
	private Util() { }
	
	public static <E> void applyFilter(Collection<E> collection, Filter<E> filter) {
		Iterator<E> iterator = collection.iterator();
		while (iterator.hasNext()) {
			E element = iterator.next();
			if (!filter.accept(element)) {
				iterator.remove();
			}
		}
	}

}
