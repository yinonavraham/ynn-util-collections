package ynn.util.collections.sql;

import static ynn.util.collections.sql.Util.adaptValuePredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public final class Predicates {
	
	private Predicates() {}
	
	/*
	 * AND
	 */
	
	public static <E> Predicate<E> and(Predicate<E> predicate1, Predicate<E> predicate2) {
		@SuppressWarnings("unchecked")
		List<Predicate<E>> predicates = Arrays.asList(predicate1, predicate2);
		return new AndPredicate<E>(predicates);
	}
	
	public static <E> Predicate<E> and(Predicate<E> predicate1, Predicate<E> predicate2, Predicate<E> predicate3) {
		@SuppressWarnings("unchecked")
		List<Predicate<E>> predicates = Arrays.asList(predicate1, predicate2, predicate3);
		return new AndPredicate<E>(predicates);
	}
	
	public static <E> Predicate<E> and(Predicate<E> predicate1, Predicate<E> predicate2, Predicate<E> predicate3, Predicate<E> predicate4) {
		@SuppressWarnings("unchecked")
		List<Predicate<E>> predicates = Arrays.asList(predicate1, predicate2, predicate3, predicate4);
		return new AndPredicate<E>(predicates);
	}
	
	public static <E> Predicate<E> and(Predicate<E> predicate1, Predicate<E> predicate2, Predicate<E> predicate3, Predicate<E> predicate4, Predicate<E>... otherPredicates) {
		@SuppressWarnings("unchecked")
		List<Predicate<E>> predicates = new ArrayList<Predicate<E>>(Arrays.asList(predicate1, predicate2, predicate3, predicate4));
		predicates.addAll(Arrays.asList((Predicate<E>[])otherPredicates));
		return new AndPredicate<E>(predicates);
	}
	
	public static <E> Predicate<E> and(List<Predicate<E>> predicates) {
		return new AndPredicate<E>(predicates);
	}
	
	/*
	 * OR
	 */
	
	public static <E> Predicate<E> or(Predicate<E> predicate1, Predicate<E> predicate2) {
		@SuppressWarnings("unchecked")
		List<Predicate<E>> predicates = Arrays.asList(predicate1, predicate2);
		return new OrPredicate<E>(predicates);
	}
	
	public static <E> Predicate<E> or(Predicate<E> predicate1, Predicate<E> predicate2, Predicate<E> predicate3) {
		@SuppressWarnings("unchecked")
		List<Predicate<E>> predicates = Arrays.asList(predicate1, predicate2, predicate3);
		return new OrPredicate<E>(predicates);
	}
	
	public static <E> Predicate<E> or(Predicate<E> predicate1, Predicate<E> predicate2, Predicate<E> predicate3, Predicate<E> predicate4) {
		@SuppressWarnings("unchecked")
		List<Predicate<E>> predicates = Arrays.asList(predicate1, predicate2, predicate3, predicate4);
		return new OrPredicate<E>(predicates);
	}
	
	public static <E> Predicate<E> or(Predicate<E> predicate1, Predicate<E> predicate2, Predicate<E> predicate3, Predicate<E> predicate4, Predicate<E>... otherPredicates) {
		@SuppressWarnings("unchecked")
		List<Predicate<E>> predicates = new ArrayList<Predicate<E>>(Arrays.asList(predicate1, predicate2, predicate3, predicate4));
		predicates.addAll(Arrays.asList((Predicate<E>[])otherPredicates));
		return new OrPredicate<E>(predicates);
	}
	
	public static <E> Predicate<E> or(List<Predicate<E>> predicates) {
		return new OrPredicate<E>(predicates);
	}
	
	/*
	 * NOT
	 */
	
	public static <E> Predicate<E> not(Predicate<E> predicate) {
		return new NotPredicate<E>(predicate);
	}
	
	/*
	 * IS NULL
	 */
	
	public static <E> Predicate<E> isNull() {
		return new IsNullPredicate<E>();
	}
	
	public static <E> Predicate<E> isNull(Class<E> elementClass) {
		if (elementClass == null) throw new IllegalArgumentException("elementClass cannot be null");
		return isNull();
	}
	
	public static <E, V> Predicate<E> isNull(ElementValueProvider<E, V> valueProvider) {
		final Predicate<V> isNull = isNull();
		return adaptValuePredicate(isNull, valueProvider);
	}
	
	// EQUALS
	
	public static <E> Predicate<E> eq(E otherElement) {
		return new EqualsPredicate<E>(otherElement);
	}
	
	public static <E, V> Predicate<E> eq(ElementValueProvider<E, V> valueProvider, V value) {
		return adaptValuePredicate(eq(value), valueProvider);
	}
	
	// LESS THAN
	
	public static <E extends Comparable<E>> Predicate<E> lessThan(E otherElement) {
		return new LessThanPredicate<E>(otherElement);
	}
	
	public static <E, V extends Comparable<V>> Predicate<E> lessThan(ElementValueProvider<E, V> valueProvider, V value) {
		return adaptValuePredicate(lessThan(value), valueProvider);
	}
	
	// LESS THAN OR EQUALS
	
	public static <E extends Comparable<E>> Predicate<E> lessThanOrEquals(E otherElement) {
		return new LessThanOrEqualsPredicate<E>(otherElement);
	}
	
	public static <E, V extends Comparable<V>> Predicate<E> lessThanOrEquals(ElementValueProvider<E, V> valueProvider, V value) {
		return adaptValuePredicate(lessThanOrEquals(value), valueProvider);
	}
	
	// GREATER THAN
	
	public static <E extends Comparable<E>> Predicate<E> greaterThan(E otherElement) {
		return new LessThanPredicate<E>(otherElement);
	}
	
	public static <E, V extends Comparable<V>> Predicate<E> greaterThan(ElementValueProvider<E, V> valueProvider, V value) {
		return adaptValuePredicate(greaterThan(value), valueProvider);
	}
	
	// GREATER THAN OR EQUALS
	
	public static <E extends Comparable<E>> Predicate<E> greaterThanOrEquals(E otherElement) {
		return new LessThanOrEqualsPredicate<E>(otherElement);
	}
	
	public static <E, V extends Comparable<V>> Predicate<E> greaterThanOrEquals(ElementValueProvider<E, V> valueProvider, V value) {
		return adaptValuePredicate(greaterThanOrEquals(value), valueProvider);
	}
	
	// BETWEEN
	
	public static <E extends Comparable<E>> Predicate<E> between(E startElement, E endElement) {
		return new BetweenPredicate<E>(startElement, endElement);
	}
	
	public static <E, V extends Comparable<V>> Predicate<E> between(ElementValueProvider<E, V> valueProvider, V startValue, V endValue) {
		return adaptValuePredicate(between(startValue, endValue), valueProvider);
	}
	
	/*
	 * IN
	 */
	
	public static <E> Predicate<E> in(Collection<E> collection) {
		return new InPredicate<E>(collection);
	}
	
	public static <E> Predicate<E> in(E element1) {
		@SuppressWarnings("unchecked")
		List<E> collection = Arrays.asList(element1);
		return in(collection);
	}
	
	public static <E> Predicate<E> in(E element1, E element2) {
		@SuppressWarnings("unchecked")
		List<E> collection = Arrays.asList(element1, element2);
		return in(collection);
	}
	
	public static <E> Predicate<E> in(E element1, E element2, E element3) {
		@SuppressWarnings("unchecked")
		List<E> collection = Arrays.asList(element1, element2, element3);
		return in(collection);
	}
	
	public static <E> Predicate<E> in(E element1, E element2, E element3, E element4) {
		@SuppressWarnings("unchecked")
		List<E> collection = Arrays.asList(element1, element2, element3, element4);
		return in(collection);
	}
	
	public static <E> Predicate<E> in(E element1, E element2, E element3, E element4, E... otherElements) {
		@SuppressWarnings("unchecked")
		List<E> collection = new ArrayList<E>(Arrays.asList(element1, element2, element3, element4));
		collection.addAll(Arrays.asList((E[])otherElements));
		return in(collection);
	}
	
	public static <E, V> Predicate<E> in(ElementValueProvider<E, V> valueProvider, Collection<V> collection) {
		return adaptValuePredicate(in(collection), valueProvider);
	}
	
	/*
	 * ALWAYS TRUE
	 */
	
	public static <E> Predicate<E> alwaysTrue() {
		return new Predicate<E>() {
			@Override
			public boolean satisfiedBy(E element) {
				return true;
			}
		};
	}
	
	public static <E> Predicate<E> alwaysTrue(Class<E> elementClass) {
		if (elementClass == null) throw new IllegalArgumentException("elementClass cannot be null");
		return alwaysTrue();
	}
	
	/*
	 * ALWAYS FALSE
	 */
	
	public static <E> Predicate<E> alwaysFalse() {
		return new Predicate<E>() {
			@Override
			public boolean satisfiedBy(E element) {
				return false;
			}
		};
	}
	
	public static <E> Predicate<E> alwaysFalse(Class<E> elementClass) {
		if (elementClass == null) throw new IllegalArgumentException("elementClass cannot be null");
		return alwaysFalse();
	}
	
	/*
	 * STRING PREDICATES
	 */
	
	public static final class StringPredicates {
		
		private StringPredicates() { }
		
		// EQUALS
		
		public static Predicate<String> stringEquals(final String s) {
			return stringEquals(s, false);
		}
		
		public static Predicate<String> stringEquals(final String s, boolean ignoreCase) {
			if (s == null) {
				return new Predicate<String>() {
					@Override
					public boolean satisfiedBy(String element) {
						return element == null;
					}
				};
			} else if (ignoreCase) {
				return new Predicate<String>() {
					@Override
					public boolean satisfiedBy(String element) {
						return s.equalsIgnoreCase(element);
					}
				};
			} else {
				return new Predicate<String>() {
					@Override
					public boolean satisfiedBy(String element) {
						return s.equals(element);
					}
				};
			}
		}
		
		public static <E> Predicate<E> stringEquals(ElementValueProvider<E, String> valueProvider, String s, boolean ignoreCase) {
			return adaptValuePredicate(stringEquals(s, ignoreCase), valueProvider);
		}
		
		public static <E> Predicate<E> stringEquals(ElementValueProvider<E, String> valueProvider, String s) {
			return adaptValuePredicate(stringEquals(s), valueProvider);
		}
		
		// PRECEDES
		
		public static Predicate<String> stringPrecedes(String s) {
			return stringPrecedes(s, false);
		}
		
		public static Predicate<String> stringPrecedes(final String s, boolean ignoreCase) {
			if (s == null) throw new NullPointerException("string cannot be null");
			if (ignoreCase) {
				return new Predicate<String>() {
					@Override
					public boolean satisfiedBy(String element) {
						return s.compareToIgnoreCase(element) > 0;
					}
				};
			} else {
				return new Predicate<String>() {
					@Override
					public boolean satisfiedBy(String element) {
						return s.compareTo(element) > 0;
					}
				};
			}
		}
		
		public static <E> Predicate<E> stringPrecedes(ElementValueProvider<E, String> valueProvider, String s) {
			return adaptValuePredicate(stringPrecedes(s), valueProvider);
		}
		
		// FOLLOWS
		
		public static Predicate<String> stringFollows(String s) {
			return stringFollows(s, false);
		}
		
		public static Predicate<String> stringFollows(final String s, boolean ignoreCase) {
			if (s == null) throw new NullPointerException("string cannot be null");
			if (ignoreCase) {
				return new Predicate<String>() {
					@Override
					public boolean satisfiedBy(String element) {
						return s.compareToIgnoreCase(element) < 0;
					}
				};
			} else {
				return new Predicate<String>() {
					@Override
					public boolean satisfiedBy(String element) {
						return s.compareTo(element) < 0;
					}
				};
			}
		}
		
		public static <E> Predicate<E> stringFollows(ElementValueProvider<E, String> valueProvider, String s) {
			return adaptValuePredicate(stringFollows(s), valueProvider);
		}
		
		// EMPTY
		
		public static Predicate<String> emptyString() {
			return stringEquals("");
		}
		
		public static <E> Predicate<E> emptyString(ElementValueProvider<E, String> valueProvider) {
			return adaptValuePredicate(emptyString(), valueProvider);
		}
		
		// STARTS WITH
		
		public static Predicate<String> stringStartsWith(final String prefix) {
			if (prefix == null) throw new NullPointerException("prefix cannot be null"); 
			return new Predicate<String>() {
				@Override
				public boolean satisfiedBy(String element) {
					if (element == null) return false;
					return element.startsWith(prefix);
				}
			};
		}
		
		public static <E> Predicate<E> stringStartsWith(ElementValueProvider<E, String> valueProvider, String prefix) {
			return adaptValuePredicate(stringStartsWith(prefix), valueProvider);
		}
		
		// ENDS WITH
		
		public static Predicate<String> stringEndsWith(final String suffix) {
			if (suffix == null) throw new NullPointerException("suffix cannot be null"); 
			return new Predicate<String>() {
				@Override
				public boolean satisfiedBy(String element) {
					if (element == null) return false;
					return element.endsWith(suffix);
				}
			};
		}

		public static <E> Predicate<E> stringEndsWith(final ElementValueProvider<E, String> valueProvider, String suffix) {
			return adaptValuePredicate(stringEndsWith(suffix), valueProvider);
		}
		
		// CONTAINS
		
		public static Predicate<String> stringContains(final String substring) {
			if (substring == null) throw new NullPointerException("substring cannot be null"); 
			return new Predicate<String>() {
				@Override
				public boolean satisfiedBy(String element) {
					if (element == null) return false;
					return element.contains(substring);
				}
			};
		}

		public static <E> Predicate<E> stringContains(final ElementValueProvider<E, String> valueProvider, String substring) {
			return adaptValuePredicate(stringContains(substring), valueProvider);
		}
		
		// MATCHES
		
		public static Predicate<String> stringMatches(final Pattern pattern) {
			if (pattern == null) throw new NullPointerException("pattern cannot be null");
			return new Predicate<String>() {
				@Override
				public boolean satisfiedBy(String element) {
					if (element == null) return false;
					return pattern.matcher(element).matches();
				}
			};
		}

		public static <E> Predicate<E> stringMatches(final ElementValueProvider<E, String> valueProvider, Pattern pattern) {
			return adaptValuePredicate(stringMatches(pattern), valueProvider);
		}
		
		public static Predicate<String> stringMatches(String regex) {
			if (regex == null) throw new NullPointerException("string pattern cannot be null");
			Pattern pattern = Pattern.compile(regex);
			return stringMatches(pattern);
		}

		public static <E> Predicate<E> stringMatches(final ElementValueProvider<E, String> valueProvider, String regex) {
			return adaptValuePredicate(stringMatches(regex), valueProvider);
		}
		
	}
	
}
