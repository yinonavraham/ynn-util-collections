package ynn.util.collections.sql;

import java.util.List;

final class AndPredicate<E> extends AbstractMultiPredicate<E> implements Predicate<E> {
	
	public AndPredicate(List<Predicate<E>> predicates) {
		super(predicates);
	}

	public AndPredicate(Predicate<E> predicate, Predicate<E>[] otherPredicate) {
		super(predicate, otherPredicate);
	}

	@Override
	public boolean satisfiedBy(E element) {
		List<Predicate<E>> predicates = getPredicates();
		for (Predicate<E> predicate : predicates) {
			if (!predicate.satisfiedBy(element)) return false;
		}
		return true;
	}

}
