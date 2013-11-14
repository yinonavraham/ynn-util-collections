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
	
	/**
	 * Adapt a value predicate (which checks a value type different than the element type) to a new element predicate (which works on an element).
	 * This adapter method uses the given {@link ElementValueProvider} to extract the value from the element and pass it to the given {@link Predicate}.  
	 * @param predicate - the value predicate
	 * @param valueProvider - the provider to use to extract the value from the element
	 * @return A new {@link Predicate} which works on an element and checks a specific argument value of it, by delegating to the given predicate. 
	 */
	public static <E, V> Predicate<E> adaptValuePredicate(final Predicate<V> predicate, final ElementValueProvider<E, V> valueProvider) {
		return new Predicate<E>() {
			@Override
			public boolean satisfiedBy(E element) {
				V value = valueProvider.getValue(element);
				return predicate.satisfiedBy(value);
			}
		};
	}

}
