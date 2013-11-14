package ynn.util.collections.sql;

class BetweenPredicate<E extends Comparable<E>> implements Predicate<E> {
	
	private final E startElement;
	private final E endElement;
	
	public BetweenPredicate(E startElement, E endElement) {
		if (startElement == null) throw new IllegalArgumentException("start element cannot be null");
		if (endElement == null) throw new IllegalArgumentException("end element cannot be null");
		this.startElement = startElement;
		this.endElement = endElement;
	}

	@Override
	public boolean satisfiedBy(E element) {
		if (element == null) return false;
		return element.compareTo(startElement) >= 0 && element.compareTo(endElement) <= 0;
	}

}
