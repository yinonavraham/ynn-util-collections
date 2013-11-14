package ynn.util.collections.sql;

class GreaterThanPredicate<E extends Comparable<E>> implements Predicate<E> {
	
	private final E otherElement;
	
	public GreaterThanPredicate(E otherElement) {
		if (otherElement == null) throw new IllegalArgumentException("element cannot be null");
		this.otherElement = otherElement;
	}

	@Override
	public boolean satisfiedBy(E element) {
		if (element == null) return false;
		return element.compareTo(otherElement) > 0;
	}

}
