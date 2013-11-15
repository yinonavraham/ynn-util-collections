package ynn.util.collections.sql.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static ynn.util.collections.sql.Predicates.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ynn.util.collections.sql.ElementValueProvider;
import ynn.util.collections.sql.Predicate;
import ynn.util.collections.sql.test.mock.Person;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PredicatesTest {

	@Test
	public void testAndPredicateOfEPredicateOfE() {
		assertTrue("and(true,true) expected to be true", and(alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(true,false) expected to be false", and(alwaysTrue(), alwaysFalse()).satisfiedBy(null));
		assertFalse("and(false,true) expected to be false", and(alwaysFalse(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(false,false) expected to be false", and(alwaysFalse(), alwaysFalse()).satisfiedBy(null));
		
		// Negative: null predicate
		try {
			and(alwaysTrue(), null);
			fail("and(true,null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testAndPredicateOfEPredicateOfEPredicateOfE() {
		assertTrue("and(true,true,true) expected to be true", and(alwaysTrue(), alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(false,true,true) expected to be false", and(alwaysFalse(), alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(true,false,true) expected to be false", and(alwaysTrue(), alwaysFalse(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(true,true,false) expected to be false", and(alwaysTrue(), alwaysTrue(), alwaysFalse()).satisfiedBy(null));
		
		// Negative: null predicate
		try {
			and(alwaysTrue(), alwaysTrue(), null);
			fail("and(true,true,null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testAndPredicateOfEPredicateOfEPredicateOfEPredicateOfE() {
		assertTrue("and(true,true,true,true) expected to be true", and(alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(false,true,true,true) expected to be false", and(alwaysFalse(), alwaysTrue(), alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(true,false,true,true) expected to be false", and(alwaysTrue(), alwaysFalse(), alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(true,true,false,true) expected to be false", and(alwaysTrue(), alwaysTrue(), alwaysFalse(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(true,true,true,false) expected to be false", and(alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysFalse()).satisfiedBy(null));
		
		// Negative: null predicate
		try {
			and(alwaysTrue(), alwaysTrue(), alwaysTrue(), null);
			fail("and(true,true,true,null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAndPredicateOfEPredicateOfEPredicateOfEPredicateOfEPredicateOfEArray() {
		assertTrue("and(true,true,true,true,true,true,true,true) expected to be true", and(alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(false,true,true,true,true,true,true,true) expected to be false", and(alwaysFalse(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(true,false,true,true,true,true,true,true) expected to be false", and(alwaysTrue(), alwaysFalse(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(true,true,false,true,true,true,true,true) expected to be false", and(alwaysTrue(), alwaysTrue(), alwaysFalse(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(true,true,true,false,true,true,true,true) expected to be false", and(alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysFalse(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(true,true,true,true,false,true,true,true) expected to be false", and(alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysFalse(), alwaysTrue(), alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(true,true,true,true,true,false,true,true) expected to be false", and(alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysFalse(), alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(true,true,true,true,true,true,false,true) expected to be false", and(alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysFalse(), alwaysTrue()).satisfiedBy(null));
		assertFalse("and(true,true,true,true,true,true,true,false) expected to be false", and(alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysFalse()).satisfiedBy(null));
		
		// Negative: null predicate
		try {
			and(alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), null);
			fail("and(true,true,true,true,true,true,true,null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAndListOfPredicateOfE() {
		assertTrue("and([true]) expected to be true", and(Arrays.asList(alwaysTrue())).satisfiedBy(null));
		assertTrue("and([true,true,true]) expected to be true", and(Arrays.asList(alwaysTrue(), alwaysTrue(), alwaysTrue())).satisfiedBy(null));
		assertFalse("and([false,true,true]) expected to be false", and(Arrays.asList(alwaysFalse(), alwaysTrue(), alwaysTrue())).satisfiedBy(null));
		assertFalse("and([true,false,true]) expected to be false", and(Arrays.asList(alwaysTrue(), alwaysFalse(), alwaysTrue())).satisfiedBy(null));
		assertFalse("and([true,true,false]) expected to be false", and(Arrays.asList(alwaysTrue(), alwaysTrue(), alwaysFalse())).satisfiedBy(null));
		
		List<Predicate<Object>> predicates;
		
		// Negative: null list
		try {
			predicates = null;
			and(predicates);
			fail("and(null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
		
		// Negative: empty list
		try {
			predicates = Collections.emptyList();
			and(predicates);
			fail("and([]): Exception was expected");
		} catch (Exception e) {
			// OK
		}
		
		// Negative: list with a null predicate
		try {
			predicates = Arrays.asList(alwaysTrue(), alwaysTrue(), null, alwaysFalse());
			and(predicates);
			fail("and([true,true,null]): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testOrPredicateOfEPredicateOfE() {
		assertTrue("or(true,true) expected to be true", or(alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertTrue("or(true,false) expected to be true", or(alwaysTrue(), alwaysFalse()).satisfiedBy(null));
		assertTrue("or(false,true) expected to be true", or(alwaysFalse(), alwaysTrue()).satisfiedBy(null));
		assertFalse("or(false,false) expected to be false", or(alwaysFalse(), alwaysFalse()).satisfiedBy(null));
		
		// Negative: null predicate
		try {
			or(alwaysTrue(), null);
			fail("or(true,null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testOrPredicateOfEPredicateOfEPredicateOfE() {
		assertTrue("or(true,true,true) expected to be true", or(alwaysTrue(), alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertTrue("or(true,false,false) expected to be true", or(alwaysTrue(), alwaysFalse(), alwaysFalse()).satisfiedBy(null));
		assertTrue("or(false,true,false) expected to be true", or(alwaysFalse(), alwaysTrue(), alwaysFalse()).satisfiedBy(null));
		assertTrue("or(false,false,true) expected to be true", or(alwaysFalse(), alwaysFalse(), alwaysTrue()).satisfiedBy(null));
		assertFalse("or(false,false,false) expected to be false", or(alwaysFalse(), alwaysFalse(), alwaysFalse()).satisfiedBy(null));
		
		// Negative: null predicate
		try {
			or(alwaysTrue(), alwaysTrue(), null);
			fail("or(true,true,null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testOrPredicateOfEPredicateOfEPredicateOfEPredicateOfE() {
		assertTrue("or(true,true,true,true) expected to be true", or(alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertTrue("or(true,false,false,false) expected to be true", or(alwaysTrue(), alwaysFalse(), alwaysFalse(), alwaysFalse()).satisfiedBy(null));
		assertTrue("or(false,true,false,false) expected to be true", or(alwaysFalse(), alwaysTrue(), alwaysFalse(), alwaysFalse()).satisfiedBy(null));
		assertTrue("or(false,false,true,false) expected to be true", or(alwaysFalse(), alwaysFalse(), alwaysTrue(), alwaysFalse()).satisfiedBy(null));
		assertTrue("or(false,false,false,true) expected to be true", or(alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysTrue()).satisfiedBy(null));
		assertFalse("or(false,false,false,false) expected to be false", or(alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse()).satisfiedBy(null));
		
		// Negative: null predicate
		try {
			or(alwaysTrue(), alwaysTrue(), alwaysTrue(), null);
			fail("or(true,true,true,null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testOrPredicateOfEPredicateOfEPredicateOfEPredicateOfEPredicateOfEArray() {
		assertTrue("or(true,true,true,true,true,true,true,true) expected to be true", or(alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue()).satisfiedBy(null));
		assertTrue("or(true,false,false,false,false,false,false,false) expected to be true", or(alwaysTrue(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse()).satisfiedBy(null));
		assertTrue("or(false,true,false,false,false,false,false,false) expected to be true", or(alwaysFalse(), alwaysTrue(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse()).satisfiedBy(null));
		assertTrue("or(false,false,true,false,false,false,false,false) expected to be true", or(alwaysFalse(), alwaysFalse(), alwaysTrue(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse()).satisfiedBy(null));
		assertTrue("or(false,false,false,true,false,false,false,false) expected to be true", or(alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysTrue(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse()).satisfiedBy(null));
		assertTrue("or(false,false,false,false,true,false,false,false) expected to be true", or(alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysTrue(), alwaysFalse(), alwaysFalse(), alwaysFalse()).satisfiedBy(null));
		assertTrue("or(false,false,false,false,false,true,false,false) expected to be true", or(alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysTrue(), alwaysFalse(), alwaysFalse()).satisfiedBy(null));
		assertTrue("or(false,false,false,false,false,false,true,false) expected to be true", or(alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysTrue(), alwaysFalse()).satisfiedBy(null));
		assertTrue("or(false,false,false,false,false,false,false,true) expected to be true", or(alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysTrue()).satisfiedBy(null));
		assertFalse("or(false,false,false,false,false,false,false,false) expected to be false", or(alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse(), alwaysFalse()).satisfiedBy(null));
		
		// Negative: null predicate
		try {
			or(alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), alwaysTrue(), null);
			fail("or(true,true,true,true,true,true,true,null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testOrListOfPredicateOfE() {
		assertTrue("or([true]) expected to be true", or(Arrays.asList(alwaysTrue())).satisfiedBy(null));
		assertTrue("or([true,true,true]) expected to be true", or(Arrays.asList(alwaysTrue(), alwaysTrue(), alwaysTrue())).satisfiedBy(null));
		assertTrue("or([true,false,false]) expected to be true", or(Arrays.asList(alwaysTrue(), alwaysFalse(), alwaysFalse())).satisfiedBy(null));
		assertTrue("or([false,true,false]) expected to be true", or(Arrays.asList(alwaysFalse(), alwaysTrue(), alwaysFalse())).satisfiedBy(null));
		assertTrue("or([false,false,true]) expected to be true", or(Arrays.asList(alwaysFalse(), alwaysFalse(), alwaysTrue())).satisfiedBy(null));
		assertFalse("or([false,false,false]) expected to be false", or(Arrays.asList(alwaysFalse(), alwaysFalse(), alwaysFalse())).satisfiedBy(null));
		
		List<Predicate<Object>> predicates;
		
		// Negative: null list
		try {
			predicates = null;
			or(predicates);
			fail("or(null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
		
		// Negative: empty list
		try {
			predicates = Collections.emptyList();
			or(predicates);
			fail("or([]): Exception was expected");
		} catch (Exception e) {
			// OK
		}
		
		// Negative: list with a null predicate
		try {
			predicates = Arrays.asList(alwaysTrue(), alwaysTrue(), null, alwaysFalse());
			or(predicates);
			fail("or([true,true,null]): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testNot() {
		assertFalse("not(true) expected to be false", not(alwaysTrue()).satisfiedBy(null));
		assertTrue("not(false) expected to be true", not(alwaysFalse()).satisfiedBy(null));
		
		// Negative: null predicate
		try {
			not(null);
			fail("not(null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testIsNull() {
		assertTrue("isNull(null) expected to be true", isNull().satisfiedBy(null));
		assertFalse("isNull(obj) expected to be false", isNull().satisfiedBy(new Object()));
	}

	@Test
	public void testIsNullClassOfE() {
		assertTrue("isNull(null) expected to be true", isNull(String.class).satisfiedBy(null));
		assertFalse("isNull(obj) expected to be false", isNull(String.class).satisfiedBy(""));
		
		// Negative: null predicate
		try {
			Class<?> cls = null;
			isNull(cls);
			fail("isNull(null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testIsNullElementValueProviderOfEV() {
		Person personA = new Person("A", 0, 0);
		Person personNull = new Person(null, 0, 0);
		assertFalse("isNull(personA.name) expected to be false", isNull(Person.name()).satisfiedBy(personA));
		assertTrue("isNull(personNull.name) expected to be true", isNull(Person.name()).satisfiedBy(personNull));
		
		// Negative: null value provider
		try {
			ElementValueProvider<Person, String> valueProvider = null;
			isNull(valueProvider);
			fail("isNull(null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testEqE() {
		assertTrue("eq(A,A) expected to be true", eq("A").satisfiedBy("A"));
		assertFalse("eq(A,B) expected to be false", eq("A").satisfiedBy("B"));
		assertFalse("eq(A,null) expected to be false", eq("A").satisfiedBy(null));
		assertFalse("eq(null,B) expected to be false", eq(null).satisfiedBy("B"));
		assertTrue("eq(null,null) expected to be true", eq(null).satisfiedBy(null));
	}

	@Test
	public void testEqElementValueProviderOfEVV() {
		Person personA = new Person("A", 0, 0);
		Person personB = new Person("B", 0, 0);
		Person personNull = new Person(null, 0, 0);
		
		assertTrue("eq(A,personA) expected to be true", eq(Person.name(), "A").satisfiedBy(personA));
		assertFalse("eq(A,personB) expected to be false", eq(Person.name(), "A").satisfiedBy(personB));
		assertFalse("eq(A,personNull) expected to be false", eq(Person.name(), "A").satisfiedBy(personNull));
		assertFalse("eq(null,personB) expected to be false", eq(Person.name(), null).satisfiedBy(personB));
		assertTrue("eq(null,personNull) expected to be true", eq(Person.name(), null).satisfiedBy(personNull));
		assertFalse("eq(null,null) expected to be false", eq(Person.name(), null).satisfiedBy(null));
		
		// Negative: null value provider
		try {
			eq(null, "A");
			fail("eq(null,A): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testLessThanE() {
		assertTrue("1 < 2 expected to be true", lessThan(2).satisfiedBy(1));
		assertFalse("2 < 1 expected to be false", lessThan(1).satisfiedBy(2));
		assertFalse("1 < 1 expected to be false", lessThan(1).satisfiedBy(1));
		assertFalse("null < 1 expected to be false", lessThan(1).satisfiedBy(null));
		
		// Negative: null value
		try {
			lessThan((Integer)null).satisfiedBy(1);
			fail("1 < null: Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testLessThanElementValueProviderOfEVV() {
		Person personA = new Person("A", 0, 0);
		Person personB = new Person("B", 0, 0);
		Person personNull = new Person(null, 0, 0);
		assertTrue("personA < B expected to be true", lessThan(Person.name(), "B").satisfiedBy(personA));
		assertFalse("personB < A expected to be false", lessThan(Person.name(), "A").satisfiedBy(personB));
		assertFalse("personA < A expected to be false", lessThan(Person.name(), "A").satisfiedBy(personA));
		assertFalse("personNull < A expected to be false", lessThan(Person.name(), "A").satisfiedBy(personNull));
		assertFalse("null < A expected to be true", lessThan(Person.name(), "A").satisfiedBy(null));
		
		// Negative: null value
		try {
			lessThan(Person.name(), null);
			fail("lessThan(person.name, null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
		
		// Negative: null value provider
		try {
			ElementValueProvider<Person, String> valueProvider = null;
			lessThan(valueProvider, "A");
			fail("lessThan(null, A): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testLessThanOrEqualsE() {
		assertTrue("1 <= 2 expected to be true", lessThanOrEquals(2).satisfiedBy(1));
		assertFalse("2 <= 1 expected to be false", lessThanOrEquals(1).satisfiedBy(2));
		assertTrue("1 <= 1 expected to be true", lessThanOrEquals(1).satisfiedBy(1));
		assertFalse("null <= 1 expected to be false", lessThanOrEquals(1).satisfiedBy(null));
		
		// Negative: null value
		try {
			lessThanOrEquals((Integer)null).satisfiedBy(1);
			fail("1 <= null: Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testLessThanOrEqualsElementValueProviderOfEVV() {
		Person personA = new Person("A", 0, 0);
		Person personB = new Person("B", 0, 0);
		Person personNull = new Person(null, 0, 0);
		assertTrue("personA <= B expected to be true", lessThanOrEquals(Person.name(), "B").satisfiedBy(personA));
		assertFalse("personB <= A expected to be false", lessThanOrEquals(Person.name(), "A").satisfiedBy(personB));
		assertTrue("personA <= A expected to be true", lessThanOrEquals(Person.name(), "A").satisfiedBy(personA));
		assertFalse("personNull <= A expected to be false", lessThanOrEquals(Person.name(), "A").satisfiedBy(personNull));
		assertFalse("null <= A expected to be true", lessThanOrEquals(Person.name(), "A").satisfiedBy(null));
		
		// Negative: null value
		try {
			lessThanOrEquals(Person.name(), null);
			fail("lessThanOrEquals(person.name, null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
		
		// Negative: null value provider
		try {
			ElementValueProvider<Person, String> valueProvider = null;
			lessThanOrEquals(valueProvider, "A");
			fail("lessThanOrEquals(null, A): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testGreaterThanE() {
		assertTrue("2 > 1 expected to be true", greaterThan(1).satisfiedBy(2));
		assertFalse("1 > 2 expected to be false", greaterThan(2).satisfiedBy(1));
		assertFalse("1 > 1 expected to be false", greaterThan(1).satisfiedBy(1));
		assertFalse("null > 1 expected to be false", greaterThan(1).satisfiedBy(null));
		
		// Negative: null value
		try {
			greaterThan((Integer)null).satisfiedBy(1);
			fail("1 > null: Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testGreaterThanElementValueProviderOfEVV() {
		Person personA = new Person("A", 0, 0);
		Person personB = new Person("B", 0, 0);
		Person personNull = new Person(null, 0, 0);
		assertFalse("personA > B expected to be false", greaterThan(Person.name(), "B").satisfiedBy(personA));
		assertTrue("personB > A expected to be true", greaterThan(Person.name(), "A").satisfiedBy(personB));
		assertFalse("personA > A expected to be false", greaterThan(Person.name(), "A").satisfiedBy(personA));
		assertFalse("personNull > A expected to be false", greaterThan(Person.name(), "A").satisfiedBy(personNull));
		assertFalse("null > A expected to be true", greaterThan(Person.name(), "A").satisfiedBy(null));
		
		// Negative: null value
		try {
			greaterThan(Person.name(), null);
			fail("greaterThan(person.name, null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
		
		// Negative: null value provider
		try {
			ElementValueProvider<Person, String> valueProvider = null;
			greaterThan(valueProvider, "A");
			fail("greaterThan(null, A): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testGreaterThanOrEqualsE() {
		assertTrue("2 >= 1 expected to be true", greaterThanOrEquals(1).satisfiedBy(2));
		assertFalse("1 >= 2 expected to be false", greaterThanOrEquals(2).satisfiedBy(1));
		assertTrue("1 >= 1 expected to be true", greaterThanOrEquals(1).satisfiedBy(1));
		assertFalse("null >= 1 expected to be false", greaterThanOrEquals(1).satisfiedBy(null));
		
		// Negative: null value
		try {
			greaterThanOrEquals((Integer)null).satisfiedBy(1);
			fail("1 >= null: Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testGreaterThanOrEqualsElementValueProviderOfEVV() {
		Person personA = new Person("A", 0, 0);
		Person personB = new Person("B", 0, 0);
		Person personNull = new Person(null, 0, 0);
		assertFalse("personA >= B expected to be false", greaterThanOrEquals(Person.name(), "B").satisfiedBy(personA));
		assertTrue("personB >= A expected to be true", greaterThanOrEquals(Person.name(), "A").satisfiedBy(personB));
		assertTrue("personA >= A expected to be true", greaterThanOrEquals(Person.name(), "A").satisfiedBy(personA));
		assertFalse("personNull >= A expected to be false", greaterThanOrEquals(Person.name(), "A").satisfiedBy(personNull));
		assertFalse("null >= A expected to be true", greaterThanOrEquals(Person.name(), "A").satisfiedBy(null));
		
		// Negative: null value
		try {
			greaterThanOrEquals(Person.name(), null);
			fail("greaterThanOrEquals(person.name, null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
		
		// Negative: null value provider
		try {
			ElementValueProvider<Person, String> valueProvider = null;
			greaterThanOrEquals(valueProvider, "A");
			fail("greaterThanOrEquals(null, A): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testBetweenEE() {
		assertTrue("2 between 1 to 3 expected to be true", between(1, 3).satisfiedBy(2));
		assertFalse("2 between 3 to 1 expected to be false", between(3, 1).satisfiedBy(2));
		assertTrue("2 between 2 to 2 expected to be true", between(2, 2).satisfiedBy(2));
		assertFalse("null between 1 to 3 expected to be false", between(1, 3).satisfiedBy(null));
		
		// Negative: null end value
		try {
			between(1, null);
			fail("between(1,null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
		
		// Negative: null start value
		try {
			between(null, 1);
			fail("between(null,1): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testBetweenElementValueProviderOfEVVV() {
		Person personB = new Person("B", 0, 0);
		Person personNull = new Person(null, 0, 0);
		assertTrue("personB between A to C expected to be true", between(Person.name(), "A", "C").satisfiedBy(personB));
		assertFalse("personB between C to A expected to be false", between(Person.name(), "C", "A").satisfiedBy(personB));
		assertTrue("personB between B to B expected to be true", between(Person.name(), "B", "B").satisfiedBy(personB));
		assertFalse("personNull between A to B expected to be false", between(Person.name(), "A", "C").satisfiedBy(personNull));
		assertFalse("null between A to B expected to be false", between(Person.name(), "A", "C").satisfiedBy(null));
		
		// Negative: null value provider
		try {
			ElementValueProvider<Person, String> valueProvider = null;
			between(valueProvider, "A", "C");
			fail("between(null,A,C): Exception was expected");
		} catch (Exception e) {
			// OK
		}
		
		// Negative: null start value
		try {
			between(Person.name(), null, "C");
			fail("between(person.name,null,C): Exception was expected");
		} catch (Exception e) {
			// OK
		}
		
		// Negative: null end value
		try {
			between(Person.name(), "A", null);
			fail("between(person.name,A,null): Exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testInCollectionOfE() {
		Collection<String> collection;
		
		collection = Arrays.asList("A", "B", "C", "D");
		assertTrue(in(collection).satisfiedBy("A"));
		assertTrue(in(collection).satisfiedBy("B"));
		assertTrue(in(collection).satisfiedBy("C"));
		assertTrue(in(collection).satisfiedBy("D"));
		assertFalse(in(collection).satisfiedBy("E"));
		assertFalse(in(collection).satisfiedBy(""));
		assertFalse(in(collection).satisfiedBy(null));
		
		collection = Arrays.asList(null, "A", "", "B", null, "C", "D", null);
		assertTrue(in(collection).satisfiedBy("A"));
		assertTrue(in(collection).satisfiedBy("B"));
		assertTrue(in(collection).satisfiedBy("C"));
		assertTrue(in(collection).satisfiedBy("D"));
		assertFalse(in(collection).satisfiedBy("E"));
		assertTrue(in(collection).satisfiedBy(""));
		assertTrue(in(collection).satisfiedBy(null));
		
		collection = Arrays.asList();
		assertFalse(in(collection).satisfiedBy("E"));
		assertFalse(in(collection).satisfiedBy(""));
		assertFalse(in(collection).satisfiedBy(null));

		collection = null;
		try {
			in(collection);
			fail("in(null collection): An exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testInE() {
		assertTrue("A in (A) expected to be true", in("A").satisfiedBy("A"));
		assertFalse("B in (A) expected to be false", in("A").satisfiedBy("B"));
		assertFalse("null in (A) expected to be false", in("A").satisfiedBy(null));
		assertFalse("A in (null) expected to be false", in((String)null).satisfiedBy("A"));
		assertTrue("null in (null) expected to be true", in((String)null).satisfiedBy(null));
	}

	@Test
	public void testInEE() {
		assertTrue("A in (A,B) expected to be true", in("A","B").satisfiedBy("A"));
		assertTrue("B in (A,B) expected to be true", in("A","B").satisfiedBy("B"));
		assertFalse("C in (A,B) expected to be false", in("A","B").satisfiedBy("C"));
		assertFalse("null in (A,B) expected to be false", in("A","B").satisfiedBy(null));
		assertFalse("A in (null,B) expected to be false", in((String)null, "B").satisfiedBy("A"));
		assertTrue("A in (null,A) expected to be true", in((String)null, "A").satisfiedBy("A"));
		assertTrue("null in (null,B) expected to be true", in((String)null, "B").satisfiedBy(null));
	}

	@Test
	public void testInEEE() {
		assertTrue("A in (A,B,C) expected to be true", in("A","B","C").satisfiedBy("A"));
		assertTrue("B in (A,B,C) expected to be true", in("A","B","C").satisfiedBy("B"));
		assertTrue("C in (A,B,C) expected to be true", in("A","B","C").satisfiedBy("C"));
		assertFalse("C in (A,B,C) expected to be false", in("A","B","C").satisfiedBy("D"));
		assertFalse("null in (A,B,C) expected to be false", in("A","B","C").satisfiedBy(null));
		assertFalse("A in (null,B,C) expected to be false", in((String)null,"B","C").satisfiedBy("A"));
		assertTrue("A in (null,A,B) expected to be true", in((String)null,"A","B").satisfiedBy("A"));
		assertTrue("null in (null,B,C) expected to be true", in((String)null,"B","C").satisfiedBy(null));
	}

	@Test
	public void testInEEEE() {
		assertTrue("A in (A,B,C,D) expected to be true", in("A","B","C","D").satisfiedBy("A"));
		assertTrue("B in (A,B,C,D) expected to be true", in("A","B","C","D").satisfiedBy("B"));
		assertTrue("C in (A,B,C,D) expected to be true", in("A","B","C","D").satisfiedBy("C"));
		assertTrue("D in (A,B,C,D) expected to be true", in("A","B","C","D").satisfiedBy("D"));
		assertFalse("E in (A,B,C,D) expected to be false", in("A","B","C","D").satisfiedBy("E"));
		assertFalse("null in (A,B,C,D) expected to be false", in("A","B","C","D").satisfiedBy(null));
		assertFalse("A in (null,B,C,D) expected to be false", in((String)null,"B","C","D").satisfiedBy("A"));
		assertTrue("A in (null,A,B,C) expected to be true", in((String)null,"A","B","C").satisfiedBy("A"));
		assertTrue("null in (null,B,C,D) expected to be true", in((String)null,"B","C","D").satisfiedBy(null));
	}

	@Test
	public void testInEEEEEArray() {
		assertTrue("A in (A,B,C,D,E,F,G) expected to be true", in("A","B","C","D","E","F","G").satisfiedBy("A"));
		assertTrue("B in (A,B,C,D,E,F,G) expected to be true", in("A","B","C","D","E","F","G").satisfiedBy("B"));
		assertTrue("C in (A,B,C,D,E,F,G) expected to be true", in("A","B","C","D","E","F","G").satisfiedBy("C"));
		assertTrue("D in (A,B,C,D,E,F,G) expected to be true", in("A","B","C","D","E","F","G").satisfiedBy("D"));
		assertTrue("E in (A,B,C,D,E,F,G) expected to be true", in("A","B","C","D","E","F","G").satisfiedBy("E"));
		assertTrue("F in (A,B,C,D,E,F,G) expected to be true", in("A","B","C","D","E","F","G").satisfiedBy("F"));
		assertTrue("G in (A,B,C,D,E,F,G) expected to be true", in("A","B","C","D","E","F","G").satisfiedBy("G"));
		assertFalse("H in (A,B,C,D,E,F,G) expected to be false", in("A","B","C","D","E","F","G").satisfiedBy("H"));
		assertFalse("null in (A,B,C,D,E,F,G) expected to be false", in("A","B","C","D","E","F","G").satisfiedBy(null));
		assertFalse("A in (null,B,C,D,E,F,G) expected to be false", in((String)null,"B","C","D","E","F","G").satisfiedBy("A"));
		assertTrue("A in (null,A,B,C,D,E,F,G) expected to be true", in((String)null,"A","B","C","D","E","F","G").satisfiedBy("A"));
		assertTrue("null in (null,B,C,D,E,F,G) expected to be true", in((String)null,"B","C","D","E","F","G").satisfiedBy(null));
	}

	@Test
	public void testInElementValueProviderOfEVCollectionOfV() {
		Collection<String> collection;
		Person personA = new Person("A", 1, 1.1);
		Person personB = new Person("B", 1, 1.1);
		Person personC = new Person("C", 1, 1.1);
		Person personD = new Person("D", 1, 1.1);
		Person personE = new Person("E", 1, 1.1);
		Person personEmpty = new Person("", 1, 1.1);
		Person personNull = new Person(null, 1, 1.1);
		
		collection = Arrays.asList("A", "B", "C", "D");
		assertTrue(in(Person.name(), collection).satisfiedBy(personA));
		assertTrue(in(Person.name(), collection).satisfiedBy(personB));
		assertTrue(in(Person.name(), collection).satisfiedBy(personC));
		assertTrue(in(Person.name(), collection).satisfiedBy(personD));
		assertFalse(in(Person.name(), collection).satisfiedBy(personE));
		assertFalse(in(Person.name(), collection).satisfiedBy(personEmpty));
		assertFalse(in(Person.name(), collection).satisfiedBy(personNull));
		
		collection = Arrays.asList(null, "A", "", "B", null, "C", "D", null);
		assertTrue(in(Person.name(), collection).satisfiedBy(personA));
		assertTrue(in(Person.name(), collection).satisfiedBy(personB));
		assertTrue(in(Person.name(), collection).satisfiedBy(personC));
		assertTrue(in(Person.name(), collection).satisfiedBy(personD));
		assertFalse(in(Person.name(), collection).satisfiedBy(personE));
		assertTrue(in(Person.name(), collection).satisfiedBy(personEmpty));
		assertTrue(in(Person.name(), collection).satisfiedBy(personNull));
		
		collection = Arrays.asList();
		assertFalse(in(Person.name(), collection).satisfiedBy(personE));
		assertFalse(in(Person.name(), collection).satisfiedBy(personEmpty));
		assertFalse(in(Person.name(), collection).satisfiedBy(personNull));

		collection = null;
		try {
			in(Person.name(), collection);
			fail("An exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testAlwaysTrue() {
		assertTrue(alwaysTrue().satisfiedBy(null));
		assertTrue(alwaysTrue().satisfiedBy(1));
		assertTrue(alwaysTrue().satisfiedBy("Hello"));
		assertTrue(alwaysTrue().satisfiedBy(true));
		assertTrue(alwaysTrue().satisfiedBy(false));
	}

	@Test
	public void testAlwaysTrueClassOfE() {
		assertTrue(alwaysTrue(String.class).satisfiedBy(null));
		assertTrue(alwaysTrue(Integer.class).satisfiedBy(1));
		assertTrue(alwaysTrue(String.class).satisfiedBy("Hello"));
		assertTrue(alwaysTrue(Boolean.class).satisfiedBy(true));
		assertTrue(alwaysTrue(Boolean.class).satisfiedBy(false));
		
		try {
			alwaysTrue(null);
			fail("An exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testAlwaysFalse() {
		assertFalse(alwaysFalse().satisfiedBy(null));
		assertFalse(alwaysFalse().satisfiedBy(1));
		assertFalse(alwaysFalse().satisfiedBy("Hello"));
		assertFalse(alwaysFalse().satisfiedBy(true));
		assertFalse(alwaysFalse().satisfiedBy(false));
	}

	@Test
	public void testAlwaysFalseClassOfE() {
		assertFalse(alwaysFalse(String.class).satisfiedBy(null));
		assertFalse(alwaysFalse(Integer.class).satisfiedBy(1));
		assertFalse(alwaysFalse(String.class).satisfiedBy("Hello"));
		assertFalse(alwaysFalse(Boolean.class).satisfiedBy(true));
		assertFalse(alwaysFalse(Boolean.class).satisfiedBy(false));
		
		try {
			alwaysFalse(null);
			fail("An exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

}
