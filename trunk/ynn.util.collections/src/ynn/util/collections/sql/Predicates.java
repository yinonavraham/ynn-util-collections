package ynn.util.collections.sql;

import java.util.ArrayList;
import java.util.Arrays;
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
		return isNull();
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
		return alwaysFalse();
	}
	
	/*
	 * STRING PREDICATES
	 */
	
	public static final class StringPredicates {
		
		private StringPredicates() { }
		
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
		
		public static Predicate<String> emptyString() {
			return stringEquals("");
		}
		
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
		
		public static Predicate<String> stringEndsWith(final String suffix) {
			if (suffix == null) throw new NullPointerException("prefix cannot be null"); 
			return new Predicate<String>() {
				@Override
				public boolean satisfiedBy(String element) {
					if (element == null) return false;
					return element.endsWith(suffix);
				}
			};
		}
		
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
		
		public static Predicate<String> stringMatches(String regex) {
			if (regex == null) throw new NullPointerException("string pattern cannot be null");
			Pattern pattern = Pattern.compile(regex);
			return stringMatches(pattern);
		}
		
	}

}
