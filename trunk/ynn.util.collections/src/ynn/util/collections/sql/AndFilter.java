package ynn.util.collections.sql;

import java.util.List;

final class AndFilter<E> extends AbstractMultiFilter<E> implements Filter<E> {
	
	public AndFilter(List<Filter<E>> filters) {
		super(filters);
	}

	public AndFilter(Filter<E> filter, Filter<E>[] otherFilters) {
		super(filter, otherFilters);
	}

	@Override
	public boolean accept(E element) {
		List<Filter<E>> filters = getFilters();
		for (Filter<E> filter : filters) {
			if (!filter.accept(element)) return false;
		}
		return true;
	}

}
