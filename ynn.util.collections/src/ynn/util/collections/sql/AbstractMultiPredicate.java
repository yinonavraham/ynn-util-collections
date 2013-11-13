package ynn.util.collections.sql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class AbstractMultiPredicate<E> implements Predicate<E> {
	
	private final List<Predicate<E>> predicates;
	
	public AbstractMultiPredicate(Predicate<E> predicate, Predicate<E>... otherPredicates) {
		this.predicates = new ArrayList<Predicate<E>>(otherPredicates.length + 1);
		addPredicate(predicate);
		for (Predicate<E> other : otherPredicates) {
			addPredicate(other);
		}
	}
	
	public AbstractMultiPredicate(List<Predicate<E>> predicates) {
		if (predicates == null) throw new NullPointerException("predicates cannot be null");
		if (predicates.isEmpty()) throw new IllegalArgumentException("predicates cannot be empty");
		this.predicates = new ArrayList<Predicate<E>>(predicates.size());
		for (Predicate<E> other : predicates) {
			addPredicate(other);
		}
	}
	
	private void addPredicate(Predicate<E> predicate) {
		if (predicate == null) throw new IllegalArgumentException("predicate cannot be null");
		predicates.add(predicate);
	}
	
	protected List<Predicate<E>> getPredicates() {
		return Collections.unmodifiableList(predicates);
	}

}
