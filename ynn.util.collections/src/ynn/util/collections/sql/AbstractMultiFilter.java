package ynn.util.collections.sql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class AbstractMultiFilter<E> implements Filter<E> {
	
	private final List<Filter<E>> filters;
	
	public AbstractMultiFilter(Filter<E> filter, Filter<E>... otherFilters) {
		this.filters = new ArrayList<Filter<E>>(otherFilters.length + 1);
		addFilter(filter);
		for (Filter<E> other : otherFilters) {
			addFilter(other);
		}
	}
	
	public AbstractMultiFilter(List<Filter<E>> filters) {
		if (filters == null) throw new NullPointerException("filters cannot be null");
		if (filters.isEmpty()) throw new IllegalArgumentException("filters cannot be empty");
		this.filters = new ArrayList<Filter<E>>(filters.size());
		for (Filter<E> other : filters) {
			addFilter(other);
		}
	}
	
	private void addFilter(Filter<E> filter) {
		if (filter == null) throw new IllegalArgumentException("filter cannot be null");
		filters.add(filter);
	}
	
	protected List<Filter<E>> getFilters() {
		return Collections.unmodifiableList(filters);
	}

}
