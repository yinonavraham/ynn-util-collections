package ynn.util.collections.sql.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ynn.util.collections.sql.Filter;
import ynn.util.collections.sql.SqlLikeCollections;
import ynn.util.collections.sql.Updater;

public class UpdatableArrayListTest {

	@Test
	public void testUpdateAll() {
		List<ElementMock> original = Arrays.asList(
				new ElementMock("A"),
				new ElementMock("B"),
				new ElementMock("C"),
				new ElementMock("D"),
				new ElementMock("E"),
				new ElementMock("F"),
				new ElementMock("G"));
		List<ElementMock> expected = Arrays.asList(
				new ElementMock("X"),
				new ElementMock("X"),
				new ElementMock("X"),
				new ElementMock("X"),
				new ElementMock("X"),
				new ElementMock("X"),
				new ElementMock("X"));
		SqlLikeCollections.update(original).set(elementValueTo("X"));
		Assert.assertArrayEquals("Updated array is not ass expected", expected.toArray(), original.toArray());
	}

	@Test
	public void testUpdateWhereNull() {
		List<ElementMock> original = Arrays.asList(
				new ElementMock(null),
				new ElementMock("A"),
				new ElementMock("B"),
				new ElementMock("C"),
				new ElementMock(null),
				new ElementMock(null),
				new ElementMock("D"),
				new ElementMock("E"),
				new ElementMock("F"),
				new ElementMock("G"),
				new ElementMock(null));
		List<ElementMock> expected = Arrays.asList(
				new ElementMock("X"),
				new ElementMock("A"),
				new ElementMock("B"),
				new ElementMock("C"),
				new ElementMock("X"),
				new ElementMock("X"),
				new ElementMock("D"),
				new ElementMock("E"),
				new ElementMock("F"),
				new ElementMock("G"),
				new ElementMock("X"));
		SqlLikeCollections.update(original).where(elementValueIs(null)).set(elementValueTo("X"));
		Assert.assertArrayEquals("Updated array is not ass expected", expected.toArray(), original.toArray());
	}
	
	/*
	 * Helpers
	 */
	
	private Updater<ElementMock> elementValueTo(final String value) {
		return new Updater<ElementMock>() {
			@Override
			public void update(ElementMock element) {
				element.value = value;
			}
		};
	}
	
	private Filter<ElementMock> elementValueIs(final String value) {
		return new Filter<ElementMock>() {
			@Override
			public boolean accept(ElementMock element) {
				if (value == null) return element.value == null;
				return value.equals(element.value);
			}
		};
	}
	
	private class ElementMock {

		String value;
		
		public ElementMock(String value) {
			this.value = value;
		}
		
		@Override
		public boolean equals(Object obj) {
			return value.equals(((ElementMock)obj).value);
		}
		
		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}

}
