package ynn.util.collections.sql;

final class IsNullFilter<E> implements Filter<E> {

	public IsNullFilter() { }

	@Override
	public boolean accept(E element) {
		return element == null;
	}

}
