package ynn.util.collections.sql;

public interface Predicate<E> {
	
	boolean satisfiedBy(E element);

}
