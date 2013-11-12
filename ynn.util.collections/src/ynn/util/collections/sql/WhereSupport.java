package ynn.util.collections.sql;

import java.util.Collection;

interface WhereSupport<E, C extends Collection<E>> {
	
	/**
	 * Filters out from the collection all elements which are not accepted by the given filter. 
	 * This method modifies this collection. 
	 * @param filter
	 * @return This collection.
	 */
	C where(Filter<E> filter);

}
