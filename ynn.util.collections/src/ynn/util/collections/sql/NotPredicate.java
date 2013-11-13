package ynn.util.collections.sql;

final class NotPredicate<E> implements Predicate<E> {

	private final Predicate<E> predicate;

	public NotPredicate(Predicate<E> predicate) {
		if (predicate == null) throw new NullPointerException("predicate cannot be null");
		this.predicate = predicate;
	}

	@Override
	public boolean satisfiedBy(E element) {
		return !predicate.satisfiedBy(element);
	}

}
