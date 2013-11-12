package ynn.util.collections.sql;

import java.util.Collection;
import java.util.List;

public final class SqlLikeCollections {
	
	private SqlLikeCollections() { }
	
	public static <E> QueryableCollection<E> selectFrom(Collection<E> collection) {
		return new QueryableArrayList<E>(collection);
	}
	
	public static <E> QueryableList<E> selectFrom(List<E> list) {
		return new QueryableArrayList<E>(list);
	}
	
	public static <E> UpdatableCollection<E> update(Collection<E> collection) {
		return new UpdatableArrayList<E>(collection);
	}

}
