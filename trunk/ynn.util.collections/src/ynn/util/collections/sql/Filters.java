package ynn.util.collections.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public final class Filters {
	
	private Filters() {}
	
	/*
	 * AND
	 */
	
	public static <E> Filter<E> and(Filter<E> filter1, Filter<E> filter2) {
		@SuppressWarnings("unchecked")
		List<Filter<E>> filters = Arrays.asList(filter1, filter2);
		return new AndFilter<E>(filters);
	}
	
	public static <E> Filter<E> and(Filter<E> filter1, Filter<E> filter2, Filter<E> filter3) {
		@SuppressWarnings("unchecked")
		List<Filter<E>> filters = Arrays.asList(filter1, filter2, filter3);
		return new AndFilter<E>(filters);
	}
	
	public static <E> Filter<E> and(Filter<E> filter1, Filter<E> filter2, Filter<E> filter3, Filter<E> filter4) {
		@SuppressWarnings("unchecked")
		List<Filter<E>> filters = Arrays.asList(filter1, filter2, filter3, filter4);
		return new AndFilter<E>(filters);
	}
	
	public static <E> Filter<E> and(Filter<E> filter1, Filter<E> filter2, Filter<E> filter3, Filter<E> filter4, Filter<E>... otherFilters) {
		@SuppressWarnings("unchecked")
		List<Filter<E>> filters = new ArrayList<Filter<E>>(Arrays.asList(filter1, filter2, filter3, filter4));
		filters.addAll(Arrays.asList((Filter<E>[])otherFilters));
		return new AndFilter<E>(filters);
	}
	
	public static <E> Filter<E> and(List<Filter<E>> filters) {
		return new AndFilter<E>(filters);
	}
	
	/*
	 * OR
	 */
	
	public static <E> Filter<E> or(Filter<E> filter1, Filter<E> filter2) {
		@SuppressWarnings("unchecked")
		List<Filter<E>> filters = Arrays.asList(filter1, filter2);
		return new OrFilter<E>(filters);
	}
	
	public static <E> Filter<E> or(Filter<E> filter1, Filter<E> filter2, Filter<E> filter3) {
		@SuppressWarnings("unchecked")
		List<Filter<E>> filters = Arrays.asList(filter1, filter2, filter3);
		return new OrFilter<E>(filters);
	}
	
	public static <E> Filter<E> or(Filter<E> filter1, Filter<E> filter2, Filter<E> filter3, Filter<E> filter4) {
		@SuppressWarnings("unchecked")
		List<Filter<E>> filters = Arrays.asList(filter1, filter2, filter3, filter4);
		return new OrFilter<E>(filters);
	}
	
	public static <E> Filter<E> or(Filter<E> filter1, Filter<E> filter2, Filter<E> filter3, Filter<E> filter4, Filter<E>... otherFilters) {
		@SuppressWarnings("unchecked")
		List<Filter<E>> filters = new ArrayList<Filter<E>>(Arrays.asList(filter1, filter2, filter3, filter4));
		filters.addAll(Arrays.asList((Filter<E>[])otherFilters));
		return new OrFilter<E>(filters);
	}
	
	public static <E> Filter<E> or(List<Filter<E>> filters) {
		return new OrFilter<E>(filters);
	}
	
	/*
	 * NOT
	 */
	
	public static <E> Filter<E> not(Filter<E> filter) {
		return new NotFilter<E>(filter);
	}
	
	/*
	 * IS NULL
	 */
	
	public static <E> Filter<E> isNull() {
		return new IsNullFilter<E>();
	}
	
	public static <E> Filter<E> isNull(Class<E> elementClass) {
		return isNull();
	}
	
	/*
	 * ALWAYS TRUE
	 */
	
	public static <E> Filter<E> alwaysTrue() {
		return new Filter<E>() {
			@Override
			public boolean accept(E element) {
				return true;
			}
		};
	}
	
	public static <E> Filter<E> alwaysTrue(Class<E> elementClass) {
		return alwaysTrue();
	}
	
	/*
	 * ALWAYS FALSE
	 */
	
	public static <E> Filter<E> alwaysFalse() {
		return new Filter<E>() {
			@Override
			public boolean accept(E element) {
				return false;
			}
		};
	}
	
	public static <E> Filter<E> alwaysFalse(Class<E> elementClass) {
		return alwaysFalse();
	}
	
	/*
	 * STRING FILTERS
	 */
	
	public static final class StringFilters {
		
		private StringFilters() { }
		
		public static Filter<String> stringEquals(final String s) {
			return stringEquals(s, false);
		}
		
		public static Filter<String> stringEquals(final String s, boolean ignoreCase) {
			if (s == null) {
				return new Filter<String>() {
					@Override
					public boolean accept(String element) {
						return element == null;
					}
				};
			} else if (ignoreCase) {
				return new Filter<String>() {
					@Override
					public boolean accept(String element) {
						return s.equalsIgnoreCase(element);
					}
				};
			} else {
				return new Filter<String>() {
					@Override
					public boolean accept(String element) {
						return s.equals(element);
					}
				};
			}
		}
		
		public static Filter<String> stringPrecedes(String s) {
			return stringPrecedes(s, false);
		}
		
		public static Filter<String> stringPrecedes(final String s, boolean ignoreCase) {
			if (s == null) throw new NullPointerException("string cannot be null");
			if (ignoreCase) {
				return new Filter<String>() {
					@Override
					public boolean accept(String element) {
						return s.compareToIgnoreCase(element) > 0;
					}
				};
			} else {
				return new Filter<String>() {
					@Override
					public boolean accept(String element) {
						return s.compareTo(element) > 0;
					}
				};
			}
		}
		
		public static Filter<String> stringFollows(String s) {
			return stringFollows(s, false);
		}
		
		public static Filter<String> stringFollows(final String s, boolean ignoreCase) {
			if (s == null) throw new NullPointerException("string cannot be null");
			if (ignoreCase) {
				return new Filter<String>() {
					@Override
					public boolean accept(String element) {
						return s.compareToIgnoreCase(element) < 0;
					}
				};
			} else {
				return new Filter<String>() {
					@Override
					public boolean accept(String element) {
						return s.compareTo(element) < 0;
					}
				};
			}
		}
		
		public static Filter<String> emptyString() {
			return stringEquals("");
		}
		
		public static Filter<String> stringStartsWith(final String prefix) {
			if (prefix == null) throw new NullPointerException("prefix cannot be null"); 
			return new Filter<String>() {
				@Override
				public boolean accept(String element) {
					if (element == null) return false;
					return element.startsWith(prefix);
				}
			};
		}
		
		public static Filter<String> stringEndsWith(final String suffix) {
			if (suffix == null) throw new NullPointerException("prefix cannot be null"); 
			return new Filter<String>() {
				@Override
				public boolean accept(String element) {
					if (element == null) return false;
					return element.endsWith(suffix);
				}
			};
		}
		
		public static Filter<String> stringContains(final String substring) {
			if (substring == null) throw new NullPointerException("substring cannot be null"); 
			return new Filter<String>() {
				@Override
				public boolean accept(String element) {
					if (element == null) return false;
					return element.contains(substring);
				}
			};
		}
		
		public static Filter<String> stringMatches(final Pattern pattern) {
			if (pattern == null) throw new NullPointerException("pattern cannot be null");
			return new Filter<String>() {
				@Override
				public boolean accept(String element) {
					if (element == null) return false;
					return pattern.matcher(element).matches();
				}
			};
		}
		
		public static Filter<String> stringMatches(String regex) {
			if (regex == null) throw new NullPointerException("string pattern cannot be null");
			Pattern pattern = Pattern.compile(regex);
			return stringMatches(pattern);
		}
		
	}

}
