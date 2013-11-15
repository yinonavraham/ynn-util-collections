package ynn.util.collections.sql.test;

import static ynn.util.collections.sql.Predicates.isNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ynn.util.collections.sql.SqlLikeCollections;
import ynn.util.collections.sql.test.mock.Person;

public class UpdatableArrayListTest {

	@Test
	public void testUpdateAll() {
		List<Person> original = Arrays.asList(
				new Person("A", 1, 1.1),
				new Person("B", 2, 2.2),
				new Person("C", 3, 3.3),
				new Person("D", 4, 4.4),
				new Person("E", 5, 5.5),
				new Person("F", 6, 6.6),
				new Person("G", 7, 7.7));
		List<Person> expected = Arrays.asList(
				new Person("X", 1, 1.1),
				new Person("X", 2, 2.2),
				new Person("X", 3, 3.3),
				new Person("X", 4, 4.4),
				new Person("X", 5, 5.5),
				new Person("X", 6, 6.6),
				new Person("X", 7, 7.7));
		SqlLikeCollections.update(original).set(Person.nameTo("X"));
		Assert.assertArrayEquals("Updated array is not ass expected", expected.toArray(), original.toArray());
	}

	@Test
	public void testUpdateWhereNull() {
		List<Person> original = Arrays.asList(
				new Person(null, -1, -1),
				new Person("A", 1, 1.1),
				new Person("B", 2, 2.2),
				new Person("C", 3, 3.3),
				new Person(null, -1, -1),
				new Person(null, -1, -1),
				new Person("D", 4, 4.4),
				new Person("E", 5, 5.5),
				new Person("F", 6, 6.6),
				new Person("G", 7, 7.7),
				new Person(null, -1, -1));
		List<Person> expected = Arrays.asList(
				new Person("X", 0, -1),
				new Person("A", 1, 1.1),
				new Person("B", 2, 2.2),
				new Person("C", 3, 3.3),
				new Person("X", 0, -1),
				new Person("X", 0, -1),
				new Person("D", 4, 4.4),
				new Person("E", 5, 5.5),
				new Person("F", 6, 6.6),
				new Person("G", 7, 7.7),
				new Person("X", 0, -1));
		SqlLikeCollections.update(original)
							.where(isNull(Person.name()))
							.set(Person.nameTo("X"), 
								 Person.ageTo(0));
		Assert.assertArrayEquals("Updated array is not ass expected", expected.toArray(), original.toArray());
	}

}
