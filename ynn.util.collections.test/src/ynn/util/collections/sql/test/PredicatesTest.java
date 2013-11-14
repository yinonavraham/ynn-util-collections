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
import ynn.util.collections.sql.test.mock.PersonMock;

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
		PersonMock personA = new PersonMock("A", 0, 0);
		PersonMock personNull = new PersonMock(null, 0, 0);
		assertFalse("isNull(personA.name) expected to be false", isNull(PersonMock.name()).satisfiedBy(personA));
		assertTrue("isNull(personNull.name) expected to be true", isNull(PersonMock.name()).satisfiedBy(personNull));
		
		// Negative: null predicate
		try {
			ElementValueProvider<PersonMock, String> valueProvider = null;
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
		PersonMock personA = new PersonMock("A", 0, 0);
		PersonMock personB = new PersonMock("B", 0, 0);
		PersonMock personNull = new PersonMock(null, 0, 0);
		
		assertTrue("eq(A,personA) expected to be true", eq(PersonMock.name(), "A").satisfiedBy(personA));
		assertFalse("eq(A,personB) expected to be false", eq(PersonMock.name(), "A").satisfiedBy(personB));
		assertFalse("eq(A,personNull) expected to be false", eq(PersonMock.name(), "A").satisfiedBy(personNull));
		assertFalse("eq(null,personB) expected to be false", eq(PersonMock.name(), null).satisfiedBy(personB));
		assertTrue("eq(null,personNull) expected to be true", eq(PersonMock.name(), null).satisfiedBy(personNull));
		assertFalse("eq(null,null) expected to be false", eq(PersonMock.name(), null).satisfiedBy(null));
		
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
		// TODO Implement testLessThanE()
		fail("Not yet implemented");
	}

	@Test
	public void testLessThanElementValueProviderOfEVV() {
		// TODO Implement testLessThanElementValueProviderOfEVV()
		fail("Not yet implemented");
	}

	@Test
	public void testLessThanOrEqualsE() {
		// TODO Implement testLessThanOrEqualsE()
		fail("Not yet implemented");
	}

	@Test
	public void testLessThanOrEqualsElementValueProviderOfEVV() {
		// TODO Implement testLessThanOrEqualsElementValueProviderOfEVV()
		fail("Not yet implemented");
	}

	@Test
	public void testGreaterThanE() {
		// TODO Implement testGreaterThanE()
		fail("Not yet implemented");
	}

	@Test
	public void testGreaterThanElementValueProviderOfEVV() {
		// TODO Implement testGreaterThanElementValueProviderOfEVV()
		fail("Not yet implemented");
	}

	@Test
	public void testGreaterThanOrEqualsE() {
		// TODO Implement testGreaterThanOrEqualsE()
		fail("Not yet implemented");
	}

	@Test
	public void testGreaterThanOrEqualsElementValueProviderOfEVV() {
		// TODO Implement testGreaterThanOrEqualsElementValueProviderOfEVV()
		fail("Not yet implemented");
	}

	@Test
	public void testBetweenEE() {
		// TODO Implement testBetweenEE()
		fail("Not yet implemented");
	}

	@Test
	public void testBetweenElementValueProviderOfEVVV() {
		// TODO Implement testBetweenElementValueProviderOfEVVV()
		fail("Not yet implemented");
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
			fail("An exception was expected");
		} catch (Exception e) {
			// OK
		}
	}

	@Test
	public void testInE() {
		// TODO Implement testInE()
		fail("Not yet implemented");
	}

	@Test
	public void testInEE() {
		// TODO Implement testInEE()
		fail("Not yet implemented");
	}

	@Test
	public void testInEEE() {
		// TODO Implement testInEEE()
		fail("Not yet implemented");
	}

	@Test
	public void testInEEEE() {
		// TODO Implement testInEEEE()
		fail("Not yet implemented");
	}

	@Test
	public void testInEEEEEArray() {
		// TODO Implement testInEEEEEArray()
		fail("Not yet implemented");
	}

	@Test
	public void testInElementValueProviderOfEVCollectionOfV() {
		Collection<String> collection;
		PersonMock personA = new PersonMock("A", 1, 1.1);
		PersonMock personB = new PersonMock("B", 1, 1.1);
		PersonMock personC = new PersonMock("C", 1, 1.1);
		PersonMock personD = new PersonMock("D", 1, 1.1);
		PersonMock personE = new PersonMock("E", 1, 1.1);
		PersonMock personEmpty = new PersonMock("", 1, 1.1);
		PersonMock personNull = new PersonMock(null, 1, 1.1);
		
		collection = Arrays.asList("A", "B", "C", "D");
		assertTrue(in(PersonMock.name(), collection).satisfiedBy(personA));
		assertTrue(in(PersonMock.name(), collection).satisfiedBy(personB));
		assertTrue(in(PersonMock.name(), collection).satisfiedBy(personC));
		assertTrue(in(PersonMock.name(), collection).satisfiedBy(personD));
		assertFalse(in(PersonMock.name(), collection).satisfiedBy(personE));
		assertFalse(in(PersonMock.name(), collection).satisfiedBy(personEmpty));
		assertFalse(in(PersonMock.name(), collection).satisfiedBy(personNull));
		
		collection = Arrays.asList(null, "A", "", "B", null, "C", "D", null);
		assertTrue(in(PersonMock.name(), collection).satisfiedBy(personA));
		assertTrue(in(PersonMock.name(), collection).satisfiedBy(personB));
		assertTrue(in(PersonMock.name(), collection).satisfiedBy(personC));
		assertTrue(in(PersonMock.name(), collection).satisfiedBy(personD));
		assertFalse(in(PersonMock.name(), collection).satisfiedBy(personE));
		assertTrue(in(PersonMock.name(), collection).satisfiedBy(personEmpty));
		assertTrue(in(PersonMock.name(), collection).satisfiedBy(personNull));
		
		collection = Arrays.asList();
		assertFalse(in(PersonMock.name(), collection).satisfiedBy(personE));
		assertFalse(in(PersonMock.name(), collection).satisfiedBy(personEmpty));
		assertFalse(in(PersonMock.name(), collection).satisfiedBy(personNull));

		collection = null;
		try {
			in(PersonMock.name(), collection);
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
