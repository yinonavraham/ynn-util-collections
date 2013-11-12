package ynn.util.collections.sql;

public interface UpdatableCollection<E> extends 
	SqlLikeCollection<E>,
	WhereSupport<E, UpdatableCollection<E>> {
	
	int set(Updater<E> updater);

}
