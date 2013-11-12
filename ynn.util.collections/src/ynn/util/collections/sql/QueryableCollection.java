package ynn.util.collections.sql;


public interface QueryableCollection<E> extends 
	SqlLikeCollection<E>, 
	WhereSupport<E, QueryableCollection<E>>,
	OrderBySupport<E, QueryableList<E>>{
	
}
