package ynn.util.collections.sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

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
	public QueryableCollection<E> where(Filter<E> filter) {
		Util.applyFilter(this, filter);
		return this;
	}

	@Override
	public QueryableList<E> orderBy(Comparator<E> comparator) {
		Collections.sort(this, comparator);
		return this;
	}

}
