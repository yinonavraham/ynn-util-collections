package ynn.util.collections.sql;

import java.util.Collection;

interface WhereSupport<E, C extends Collection<E>> {
	
	/**
	 * Removes from the collection all elements which do not satisfy the given predicate. 
	 * This method modifies this collection. 
	 * @param predicate
	 * @return This collection.
	 */
	C where(Predicate<E> predicate);

}
