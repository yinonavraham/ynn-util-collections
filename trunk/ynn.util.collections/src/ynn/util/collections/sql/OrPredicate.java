package ynn.util.collections.sql;

import java.util.List;

final class OrPredicate<E> extends AbstractMultiPredicate<E> implements Predicate<E> {
	
	public OrPredicate(Predicate<E> predicate, Predicate<E>... otherPredicate) {
		super(predicate, otherPredicate);
	}
	
	public OrPredicate(List<Predicate<E>> predicates) {
		super(predicates);
	}
	
	@Override
	public boolean satisfiedBy(E element) {
		List<Predicate<E>> predicates = getPredicates();
		for (Predicate<E> predicate : predicates) {
			if (predicate.satisfiedBy(element)) return true;
		}
		return false;
	}

}
