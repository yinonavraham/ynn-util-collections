package ynn.util.collections.sql;

import java.util.ArrayList;
import java.util.Collection;

class UpdatableArrayList<E> extends ArrayList<E> implements UpdatableCollection<E> {

	private static final long serialVersionUID = -8853238821682230170L;
	
	/** 
	 * @see ArrayList#ArrayList()
	 */
	public UpdatableArrayList() {
		super();
	}
	
	/** 
	 * @see ArrayList#ArrayList(Collection)
	 */
	public UpdatableArrayList(Collection<? extends E> c) {
		super(c);
	}
	
	/** 
	 * @see ArrayList#ArrayList(int)
	 */
	public UpdatableArrayList(int initialCapacity) {
		super(initialCapacity);
	}

	@Override
	public UpdatableCollection<E> where(Filter<E> filter) {
		Util.applyFilter(this, filter);
		return this;
	}

	@Override
	public int set(Updater<E> updater) {
		for (E element : this) {
			updater.update(element);
		}
		return this.size();
	}

}
