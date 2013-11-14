package ynn.util.collections.sql;

public interface ElementValueProvider<E, V> {
	
	V getValue(E element);

}
