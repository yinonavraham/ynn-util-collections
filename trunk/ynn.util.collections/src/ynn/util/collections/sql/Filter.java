package ynn.util.collections.sql;

public interface Filter<E> {
	
	boolean accept(E element);

}
