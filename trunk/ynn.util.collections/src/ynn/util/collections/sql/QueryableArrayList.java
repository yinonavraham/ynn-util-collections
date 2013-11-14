package ynn.util.collections.sql;

import static ynn.util.collections.sql.OrderByUtil.asc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class QueryableArrayList<E> extends ArrayList<E> implements QueryableList<E> {

	private static final long serialVersionUID = -8853238821682230170L;
	
	/** 
	 * @see ArrayList#ArrayList()
	 */
	public QueryableArrayList() {
		super();
	}
	
	/** 
	 * @see ArrayList#ArrayList(Collection)
	 */
	public QueryableArrayList(Collection<? extends E> c) {
		super(c);
	}
	
	/** 
	 * @see ArrayList#ArrayList(int)
	 */
	public QueryableArrayList(int initialCapacity) {
		super(initialCapacity);
	}

	@Override
	public QueryableCollection<E> where(Predicate<E> predicate) {
		Util.removeUnsatisfyingElements(this, predicate);
		return this;
	}

	@Override
	public QueryableList<E> orderBy(Comparator<E> comparator) {
		Collections.sort(this, comparator);
		return this;
	}

	public QueryableList<E> orderBy(final List<Comparator<E>> comparators) {
		if (comparators == null) throw new IllegalArgumentException("comparators cannot be null");
		Comparator<E> comparator = new Comparator<E>() {
			@Override
			public int compare(E o1, E o2) {
				int result;
				for (Comparator<E> c : comparators) {
					result = c.compare(o1, o2);
					if (result != 0) return result;
				}
				return 0;
			}
		};
		Collections.sort(this, comparator);
		return this;
	}
	
	@Override
	public QueryableList<E> orderBy(Comparator<E> comparator1, Comparator<E> comparator2) {
		@SuppressWarnings("unchecked")
		List<Comparator<E>> comparators = Arrays.asList(comparator1, comparator2);
		return orderBy(comparators);
	}
	
	@Override
	public QueryableList<E> orderBy(Comparator<E> comparator1, Comparator<E> comparator2, Comparator<E> comparator3) {
		@SuppressWarnings("unchecked")
		List<Comparator<E>> comparators = Arrays.asList(comparator1, comparator2, comparator3);
		return orderBy(comparators);
	}
	
	@Override
	public QueryableList<E> orderBy(Comparator<E> comparator1, Comparator<E> comparator2, Comparator<E> comparator3, Comparator<E>... otherComparator) {
		@SuppressWarnings("unchecked")
		List<Comparator<E>> comparators = new ArrayList<Comparator<E>>(Arrays.asList(comparator1, comparator2, comparator3));
		comparators.addAll(Arrays.asList((Comparator<E>[])otherComparator));
		return orderBy(comparators);
	}

	@Override
	public <V extends Comparable<V>> QueryableList<E> orderBy(final ElementValueProvider<E, V> valueProvider) {
		return orderBy(asc(valueProvider));
	}

	@Override
	public <V1 extends Comparable<V1>, V2 extends Comparable<V2>> QueryableList<E> orderBy(
			final ElementValueProvider<E, V1> valueProvider1, 
			final ElementValueProvider<E, V2> valueProvider2) {
		return orderBy(asc(valueProvider1), asc(valueProvider2));
	}

	@Override
	public <V1 extends Comparable<V1>, V2 extends Comparable<V2>, V3 extends Comparable<V3>> QueryableList<E> orderBy(
			final ElementValueProvider<E, V1> valueProvider1, 
			final ElementValueProvider<E, V2> valueProvider2, 
			final ElementValueProvider<E, V3> valueProvider3) {
		return orderBy(asc(valueProvider1), asc(valueProvider2), asc(valueProvider3));
	}

}
