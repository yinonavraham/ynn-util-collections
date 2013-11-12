package ynn.util.collections.sql;

public interface QueryResultEntry<E> {
	
	E element();
	
	Object getObject(String key);
	
	String getString(String key);
	
	Integer getInteger(String key);
	
	Long getLong(String key);
	
	Long getBoolean(String key);

}
