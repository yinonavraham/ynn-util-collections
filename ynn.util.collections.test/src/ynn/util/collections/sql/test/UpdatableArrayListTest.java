package ynn.util.collections.sql.test;

import static ynn.util.collections.sql.Predicates.isNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ynn.util.collections.sql.SqlLikeCollections;
import ynn.util.collections.sql.test.mock.PersonMock;

public class UpdatableArrayListTest {

	@Test
	public void testUpdateAll() {
		List<PersonMock> original = Arrays.asList(
				new PersonMock("A", 1, 1.1),
				new PersonMock("B", 2, 2.2),
				new PersonMock("C", 3, 3.3),
				new PersonMock("D", 4, 4.4),
				new PersonMock("E", 5, 5.5),
				new PersonMock("F", 6, 6.6),
				new PersonMock("G", 7, 7.7));
		List<PersonMock> expected = Arrays.asList(
				new PersonMock("X", 1, 1.1),
				new PersonMock("X", 2, 2.2),
				new PersonMock("X", 3, 3.3),
				new PersonMock("X", 4, 4.4),
				new PersonMock("X", 5, 5.5),
				new PersonMock("X", 6, 6.6),
				new PersonMock("X", 7, 7.7));
		SqlLikeCollections.update(original).set(PersonMock.nameTo("X"));
		Assert.assertArrayEquals("Updated array is not ass expected", expected.toArray(), original.toArray());
	}

	@Test
	public void testUpdateWhereNull() {
		List<PersonMock> original = Arrays.asList(
				new PersonMock(null, -1, -1),
				new PersonMock("A", 1, 1.1),
				new PersonMock("B", 2, 2.2),
				new PersonMock("C", 3, 3.3),
				new PersonMock(null, -1, -1),
				new PersonMock(null, -1, -1),
				new PersonMock("D", 4, 4.4),
				new PersonMock("E", 5, 5.5),
				new PersonMock("F", 6, 6.6),
				new PersonMock("G", 7, 7.7),
				new PersonMock(null, -1, -1));
		List<PersonMock> expected = Arrays.asList(
				new PersonMock("X", 0, -1),
				new PersonMock("A", 1, 1.1),
				new PersonMock("B", 2, 2.2),
				new PersonMock("C", 3, 3.3),
				new PersonMock("X", 0, -1),
				new PersonMock("X", 0, -1),
				new PersonMock("D", 4, 4.4),
				new PersonMock("E", 5, 5.5),
				new PersonMock("F", 6, 6.6),
				new PersonMock("G", 7, 7.7),
				new PersonMock("X", 0, -1));
		SqlLikeCollections.update(original)
							.where(isNull(PersonMock.name()))
							.set(PersonMock.nameTo("X"), 
								 PersonMock.ageTo(0));
		Assert.assertArrayEquals("Updated array is not ass expected", expected.toArray(), original.toArray());
	}

}
