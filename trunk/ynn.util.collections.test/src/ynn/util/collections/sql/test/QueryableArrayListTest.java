package ynn.util.collections.sql.test;

import static org.junit.Assert.assertArrayEquals;
import static ynn.util.collections.sql.Filters.alwaysFalse;
import static ynn.util.collections.sql.Filters.alwaysTrue;
import static ynn.util.collections.sql.Filters.and;
import static ynn.util.collections.sql.Filters.isNull;
import static ynn.util.collections.sql.Filters.not;
import static ynn.util.collections.sql.Filters.or;
import static ynn.util.collections.sql.Filters.StringFilters.emptyString;
import static ynn.util.collections.sql.Filters.StringFilters.stringContains;
import static ynn.util.collections.sql.Filters.StringFilters.stringEndsWith;
import static ynn.util.collections.sql.Filters.StringFilters.stringEquals;
import static ynn.util.collections.sql.Filters.StringFilters.stringFollows;
import static ynn.util.collections.sql.Filters.StringFilters.stringMatches;
import static ynn.util.collections.sql.Filters.StringFilters.stringPrecedes;
import static ynn.util.collections.sql.Filters.StringFilters.stringStartsWith;
import static ynn.util.collections.sql.SqlLikeCollections.selectFrom;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import ynn.util.collections.sql.QueryableCollection;

public class QueryableArrayListTest {
	
	private static final Comparator<String> COMPARATOR_LEXI = new Comparator<String>() {
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
				.orderBy(COMPARATOR_LEXI);
		assertArrayEquals("Result list is not as expected", expected.toArray(), result.toArray());
	}

}
