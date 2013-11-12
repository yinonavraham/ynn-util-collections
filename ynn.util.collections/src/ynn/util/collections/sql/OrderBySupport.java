package ynn.util.collections.sql;

import java.util.Comparator;
import java.util.List;

interface OrderBySupport<E, C extends List<E>> {
	
	/**
	 * Sorts the elements in this collection using the given comparator.
	 * This method modifies this collection.
	 * @param comparator
	 * @return This ordered collection
	 */
	C orderBy(Comparator<E> comparator);

}
