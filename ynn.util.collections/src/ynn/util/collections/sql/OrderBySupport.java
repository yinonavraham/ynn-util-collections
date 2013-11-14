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
	
	/**
	 * Sorts the elements in this collection using the given comparators.
	 * This method modifies this collection.
	 * @param comparator1
	 * @param comparator2
	 * @return This ordered collection
	 */
	C orderBy(Comparator<E> comparator1, Comparator<E> comparator2);
	
	/**
	 * Sorts the elements in this collection using the given comparators.
	 * This method modifies this collection.
	 * @param comparator1
	 * @param comparator2
	 * @param comparator3
	 * @return This ordered collection
	 */
	C orderBy(Comparator<E> comparator1, Comparator<E> comparator2, Comparator<E> comparator3);
	
	/**
	 * Sorts the elements in this collection using the given comparators.
	 * This method modifies this collection.
	 * @param comparator1
	 * @param comparator2
	 * @param comparator3
	 * @param otherComparators
	 * @return This ordered collection
	 */
	C orderBy(Comparator<E> comparator1, Comparator<E> comparator2, Comparator<E> comparator3, Comparator<E>... otherComparators);
	
	/**
	 * Sorts the elements in this collection according to the natural order 
	 * defined by the {@link Comparable} value in the given {@link ElementValueProvider}.
	 * This method modifies this collection.
	 * @param valueProvider
	 * @return This ordered collection
	 */
	<V extends Comparable<V>> C orderBy(ElementValueProvider<E, V> valueProvider);
	
	/**
	 * Sorts the elements in this collection according to the natural order 
	 * defined by the {@link Comparable} values in the given {@link ElementValueProvider}s.
	 * This method modifies this collection.
	 * @param valueProvider1
	 * @param valueProvider2
	 * @return This ordered collection
	 */
	<V1 extends Comparable<V1>, V2 extends Comparable<V2>> C orderBy(
			ElementValueProvider<E, V1> valueProvider1, 
			ElementValueProvider<E, V2> valueProvider2);
	
	/**
	 * Sorts the elements in this collection according to the natural order 
	 * defined by the {@link Comparable} values in the given {@link ElementValueProvider}s.
	 * This method modifies this collection.
	 * @param valueProvider1
	 * @param valueProvider2
	 * @param valueProvider3
	 * @return This ordered collection
	 */
	<V1 extends Comparable<V1>, V2 extends Comparable<V2>, V3 extends Comparable<V3>> C orderBy(
			ElementValueProvider<E, V1> valueProvider1, 
			ElementValueProvider<E, V2> valueProvider2, 
			ElementValueProvider<E, V3> valueProvider3);

}
