package ynn.util.collections.sql;

class EqualsPredicate<E> implements Predicate<E> {
	
	private final E otherElement;
	
	public EqualsPredicate(E otherElement) {
		this.otherElement = otherElement;
	}

	@Override
	public boolean satisfiedBy(E element) {
		if (element == null) return otherElement == null;
		return element.equals(otherElement);
	}

}
