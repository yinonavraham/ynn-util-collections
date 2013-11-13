package ynn.util.collections.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
	
	/*
	 * WHERE
	 */

	@Override
	public UpdatableCollection<E> where(Predicate<E> predicate) {
		Util.removeUnsatisfyingElements(this, predicate);
		return this;
	}
	
	/*
	 * SET
	 */

	@Override
	public int set(Updater<E> updater) {
		for (E element : this) {
			updater.update(element);
		}
		return this.size();
	}

	@Override
	public int set(Updater<E> updater1, Updater<E> updater2) {
		@SuppressWarnings("unchecked")
		List<Updater<E>> updaters = Arrays.asList(updater1, updater2);
		set(updaters);
		return this.size();
	}

	@Override
	public int set(Updater<E> updater1, Updater<E> updater2, Updater<E> updater3) {
		@SuppressWarnings("unchecked")
		List<Updater<E>> updaters = Arrays.asList(updater1, updater2, updater3);
		set(updaters);
		return this.size();
	}

	@Override
	public int set(Updater<E> updater1, Updater<E> updater2, Updater<E> updater3, Updater<E> updater4) {
		@SuppressWarnings("unchecked")
		List<Updater<E>> updaters = Arrays.asList(updater1, updater2, updater3, updater4);
		set(updaters);
		return this.size();
	}

	@Override
	public int set(Updater<E> updater1, Updater<E> updater2, Updater<E> updater3, Updater<E> updater4, Updater<E> updater5) {
		@SuppressWarnings("unchecked")
		List<Updater<E>> updaters = Arrays.asList(updater1, updater2, updater3, updater4, updater5);
		set(updaters);
		return this.size();
	}

	@Override
	public int set(Collection<Updater<E>> updaters) {
		for (E element : this) {
			for (Updater<E> updater : updaters) {
				updater.update(element);
			}
		}
		return this.size();
	}

}
