package ynn.util.collections.sql.test;

import static org.junit.Assert.assertArrayEquals;
import static ynn.util.collections.sql.OrderByUtil.asc;
import static ynn.util.collections.sql.OrderByUtil.desc;
import static ynn.util.collections.sql.Predicates.alwaysFalse;
import static ynn.util.collections.sql.Predicates.alwaysTrue;
import static ynn.util.collections.sql.Predicates.and;
import static ynn.util.collections.sql.Predicates.in;
import static ynn.util.collections.sql.Predicates.isNull;
import static ynn.util.collections.sql.Predicates.lessThanOrEquals;
import static ynn.util.collections.sql.Predicates.not;
import static ynn.util.collections.sql.Predicates.or;
import static ynn.util.collections.sql.Predicates.StringPredicates.emptyString;
import static ynn.util.collections.sql.Predicates.StringPredicates.stringContains;
import static ynn.util.collections.sql.Predicates.StringPredicates.stringEndsWith;
import static ynn.util.collections.sql.Predicates.StringPredicates.stringEquals;
import static ynn.util.collections.sql.Predicates.StringPredicates.stringFollows;
import static ynn.util.collections.sql.Predicates.StringPredicates.stringMatches;
import static ynn.util.collections.sql.Predicates.StringPredicates.stringPrecedes;
import static ynn.util.collections.sql.Predicates.StringPredicates.stringStartsWith;
import static ynn.util.collections.sql.SqlLikeCollections.selectFrom;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import ynn.util.collections.sql.QueryableCollection;
import ynn.util.collections.sql.test.mock.Person;

public class QueryableArrayListTest {
	
	private static final Comparator<String> LEXICOGRAPHIC_ORDER = new Comparator<String>() {
		@Override
		public int compare(String e1, String e2) {
			return e1.compareTo(e2);
		}
	};

	@Test
	public void testWhereReturnAll() {
		List<String> original = Arrays.asList(
				"A", "B", "C", "D", "E", "F", "G", "H", "I"
				);
		QueryableCollection<String> result = 
				selectFrom(original)
				.where(alwaysTrue(String.class));
		assertArrayEquals("Result list is not as expected", original.toArray(), result.toArray());
	}

	@Test
	public void testWhereReturnNone() {
		List<String> original = Arrays.asList(
				"A", "B", "C", "D", "E", "F", "G", "H", "I"
				);
		QueryableCollection<String> result = 
				selectFrom(original)
				.where(alwaysFalse(String.class));
		assertArrayEquals("Result list is not as expected", new String[]{}, result.toArray());
	}

	@Test
	public void testWhereNotNull() {
		List<String> original = Arrays.asList(
				null, "A", "B", "C", null, "D", "E", "F", "G", "H", "I", null
				);
		List<String> expected = Arrays.asList(
				"A", "B", "C", "D", "E", "F", "G", "H", "I"
				);
		QueryableCollection<String> result = 
				selectFrom(original)
				.where(not(isNull(String.class)));
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}

	@Test
	public void testWhereNull() {
		List<String> original = Arrays.asList(
				null, "A", "B", "C", null, "D", "E", "F", "G", "H", "I", null
				);
		List<String> expected = Arrays.asList(
				null, null, null
				);
		QueryableCollection<String> result = 
				selectFrom(original)
				.where(isNull(String.class));
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}

	@Test
	public void testWhereNotNullOrEmpty() {
		List<String> original = Arrays.asList(
				"", "A", "B", "C", null, "D", "", "E", "F", "G", "", "H", "I", null
				);
		List<String> expected = Arrays.asList(
				"A", "B", "C", "D", "E", "F", "G", "H", "I"
				);
		QueryableCollection<String> result = 
				selectFrom(original)
				.where(
					not(
							or(
								emptyString(),
								isNull(String.class)
							)
						)
					);
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}

	@Test
	public void testWhereIn() {
		List<String> original = Arrays.asList(
				"", "A", "B", "C", null, "D", "", "E", "F", "G", "", "H", "I", null
				);
		List<String> expected = Arrays.asList(
				"A", "B", "C", "D", "E", "F", "G", "H", "I"
				);
		QueryableCollection<String> result = 
				selectFrom(original)
				.where(in(expected));
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}

	@Test
	public void testWhereInWithNull() {
		List<String> original = Arrays.asList(
				"", "A", "B", "C", null, "D", "", "E", "F", "G", "", "H", "I", null
				);
		List<String> expected = Arrays.asList(
				"A", "B", "C", null, "D", "E", "F", "G", "H", "I", null
				);
		QueryableCollection<String> result = 
				selectFrom(original)
				.where(in(null, "A", "B", "C", "D", "E", "F", "G", "H", "I"));
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}
	
	/*
	 * STRING PREDICATES
	 */

	@Test
	public void testWhereStringEquals() {
		List<String> original = Arrays.asList(
				"", "A", "B", "C", null, "D", "", "E", "F", "G", "", "H", "I", null
				);
		List<String> expected = Arrays.asList(
				"A", "B", "C"
				);
		QueryableCollection<String> result = 
				selectFrom(original)
				.where(
						or(
								stringEquals("A"),
								stringEquals("B"),
								stringEquals("C")
							)
					);
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}

	@Test
	public void testWhereStringMatches() {
		List<String> original = Arrays.asList(
				"", "A", "B", "C", null, "D", "", "E", "F", "G", "", "H", "I", null
				);
		List<String> expected = Arrays.asList(
				"A", "B", "C"
				);
		QueryableCollection<String> result = 
				selectFrom(original)
				.where(stringMatches("A|B|C"));
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}

	@Test
	public void testWhereStringStartsWith() {
		List<String> original = Arrays.asList(
				"", "Alpha", "Beta", "Charlie", null, "Delta", "", "Echo", "Fox", "Global", "", "Hello", "Israel", null
				);
		List<String> expected = Arrays.asList(
				"Delta"
				);
		QueryableCollection<String> result = 
				selectFrom(original)
				.where(stringStartsWith("Del"));
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}

	@Test
	public void testWhereStringEndsWith() {
		List<String> original = Arrays.asList(
				"", "Alpha", "Beta", "Charlie", null, "Delta", "", "Echo", "Fox", "Global", "", "Hello", "Israel", null
				);
		List<String> expected = Arrays.asList(
				"Alpha", "Beta", "Delta"
				);
		QueryableCollection<String> result = 
				selectFrom(original)
				.where(stringEndsWith("a"));
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}

	@Test
	public void testWhereStringContains() {
		List<String> original = Arrays.asList(
				"", "Alpha", "Beta", "Charlie", null, "Delta", "", "Echo", "Fox", "Global", "", "Hello", "Israel", null
				);
		List<String> expected = Arrays.asList(
				"Alpha", "Global"
				);
		Collection<String> result = 
				selectFrom(original)
				.where(
					and(
						stringContains("l"),
						not(stringContains("e"))
						)
					);
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}

	@Test
	public void testWhereStringBetween() {
		List<String> original = Arrays.asList(
				"", "Alpha", "Beta", "Charlie", null, "Delta", "", "Echo", "Fox", "Global", "", "Hello", "Israel", null
				);
		List<String> expected = Arrays.asList(
				"Delta", "Echo", "Fox"
				);
		Collection<String> result = 
				selectFrom(original)
				.where(
						and(
								not(isNull(String.class)),
								stringFollows("Charlie"),
								stringPrecedes("Global")
								)
					);
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}

	@Test
	public void testWhereNotNullOrderByLexi() {
		List<String> original = Arrays.asList(
				null, "H", "E", "I", null, "B", "D", "F", "G", "A", "C", null
				);
		List<String> expected = Arrays.asList(
				"A", "B", "C", "D", "E", "F", "G", "H", "I"
				);
		List<String> result = 
				selectFrom(original)
				.where(not(isNull(String.class)))
				.orderBy(LEXICOGRAPHIC_ORDER);
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}
	
	/*
	 * COMPLEX ELEMENTS - PERSON
	 */

	@Test
	public void testWherePersonMockAttributes() {
		List<Person> original = Arrays.asList(
				new Person("name1", 1, 1.1),
				new Person("name2", 2, 2.2),
				new Person( null, 	-1, -1),
				new Person("name3", 3, 3.3),
				new Person("", 	 0, 0),
				new Person("name4", 4, 4.4)
				);
		List<Person> expected = Arrays.asList(
				new Person("name1", 1, 1.1),
				new Person("name3", 3, 3.3),
				new Person("name4", 4, 4.4)
				);
		Collection<Person> result = 
				selectFrom(original)
				.where(
						and(
							not(isNull(Person.name())),
							not(in(Person.age(), Arrays.asList(0, 2)))
						)
					);
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}

	@Test
	public void testWherePersonMockStringAttributes() {
		List<Person> original = Arrays.asList(
				new Person("name1", 1, 1.1),
				new Person("name2", 2, 2.2),
				new Person( null, 	-1, -1),
				new Person("name3", 3, 3.3),
				new Person("", 	 0, 0),
				new Person("name4", 4, 4.4)
				);
		List<Person> expected = Arrays.asList(
				new Person("name2", 2, 2.2),
				new Person( null, 	-1, -1),
				new Person("", 	 0, 0)
				);
		Collection<Person> result = 
				selectFrom(original)
				.where(
						or(
							isNull(Person.name()),
							stringEndsWith(Person.name(), "2"),
							lessThanOrEquals(Person.age(), 0)
						)
					);
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}

	@Test
	public void testWherePersonMockNameNotNullOrEmptyOrderByName() {
		List<Person> original = Arrays.asList(
				new Person("name2", 2, 2.2),
				new Person("name1", 1, 1.1),
				new Person( null, 	-1, -1),
				new Person("name4", 4, 4.4),
				new Person("", 	 0, 0),
				new Person("name3", 3, 3.3)
				);
		List<Person> expected = Arrays.asList(
				new Person("name1", 1, 1.1),
				new Person("name2", 2, 2.2),
				new Person("name3", 3, 3.3),
				new Person("name4", 4, 4.4)
				);
		Collection<Person> result = 
				selectFrom(original)
				.where(
						and(
							not(isNull(Person.name())),
							not(emptyString(Person.name()))
						)
					)
				.orderBy(Person.name());
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}

	@Test
	public void testPersonMockOrderByAgeDescNameAsc() {
		List<Person> original = Arrays.asList(
				new Person("name2", 2, 2.2),
				new Person("name1", 1, 1.1),
				new Person("name4", 4, 4.4),
				new Person("name3", 1, 3.3),
				new Person("name5", 2, 5.5),
				new Person("name6", 3, 6.6)
				);
		List<Person> expected = Arrays.asList(
				new Person("name4", 4, 4.4),
				new Person("name6", 3, 6.6),
				new Person("name2", 2, 2.2),
				new Person("name5", 2, 5.5),
				new Person("name1", 1, 1.1),
				new Person("name3", 1, 3.3)
				);
		Collection<Person> result = 
				selectFrom(original)
				.orderBy(desc(Person.age()), 
						 asc(Person.name()));
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}
	
}
