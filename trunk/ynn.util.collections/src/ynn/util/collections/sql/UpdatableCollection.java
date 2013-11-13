package ynn.util.collections.sql;

import java.util.Collection;

public interface UpdatableCollection<E> extends 
	SqlLikeCollection<E>,
	WhereSupport<E, UpdatableCollection<E>> {
	
	int set(Updater<E> updater);
	
	int set(Updater<E> updater1, Updater<E> updater2);
	
	int set(Updater<E> updater1, Updater<E> updater2, Updater<E> updater3);
	
	int set(Updater<E> updater1, Updater<E> updater2, Updater<E> updater3, Updater<E> updater4);
	
	int set(Updater<E> updater1, Updater<E> updater2, Updater<E> updater3, Updater<E> updater4, Updater<E> updater5);
	
	int set(Collection<Updater<E>> updaters);

}
