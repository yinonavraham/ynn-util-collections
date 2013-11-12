package ynn.util.collections.sql;

import java.util.List;

final class OrFilter<E> extends AbstractMultiFilter<E> implements Filter<E> {
	
	public OrFilter(Filter<E> filter, Filter<E>... otherFilters) {
		super(filter, otherFilters);
	}
	
	public OrFilter(List<Filter<E>> filters) {
		super(filters);
	}
	
	@Override
	public boolean accept(E element) {
		List<Filter<E>> filters = getFilters();
		for (Filter<E> filter : filters) {
			if (filter.accept(element)) return true;
		}
		return false;
	}

}
