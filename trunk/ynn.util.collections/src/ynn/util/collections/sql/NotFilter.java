package ynn.util.collections.sql;

final class NotFilter<E> implements Filter<E> {

	private final Filter<E> filter;

	public NotFilter(Filter<E> filter) {
		if (filter == null) throw new NullPointerException("filter cannot be null");
		this.filter = filter;
	}

	@Override
	public boolean accept(E element) {
		return !filter.accept(element);
	}

}
