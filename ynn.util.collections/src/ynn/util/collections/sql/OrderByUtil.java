package ynn.util.collections.sql;

import java.util.Comparator;

public final class OrderByUtil {
	
	private OrderByUtil() { }
	
	public static <E, V extends Comparable<V>> Comparator<E> asc(final ElementValueProvider<E, V> valueProvider) {
		return new Comparator<E>() {
			@Override
			public int compare(E o1, E o2) {
				V value1 = valueProvider.getValue(o1);
				V value2 = valueProvider.getValue(o2);
				return value1.compareTo(value2);
			}
		};
	}
	
	public static <E, V extends Comparable<V>> Comparator<E> desc(final ElementValueProvider<E, V> valueProvider) {
		return new Comparator<E>() {
			@Override
			public int compare(E o1, E o2) {
				V value1 = valueProvider.getValue(o1);
				V value2 = valueProvider.getValue(o2);
				return value1.compareTo(value2) * -1;
			}
		};
	}

}
