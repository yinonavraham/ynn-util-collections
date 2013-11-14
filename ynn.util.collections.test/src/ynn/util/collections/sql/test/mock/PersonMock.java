package ynn.util.collections.sql.test.mock;

import ynn.util.collections.sql.ElementValueProvider;
import ynn.util.collections.sql.Updater;

public class PersonMock {
	
	private String name;
	private int age;
	private double weight;
	
	public PersonMock() { }
	
	public PersonMock(String name, int age, double weight) { 
		this.name = name;
		this.age = age;
		this.weight = weight;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return String.format("name: %s, age: %d, weight: %f", name, age, weight);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() == this.getClass()) {
			PersonMock otherElement = (PersonMock) obj;
			// Name
			if (otherElement.name == null) {
				if (name != null) return false;
			} else if (!otherElement.name.equals(name)) {
				return false;
			}
			// Age
			if (otherElement.age != age) return false;
			// Weight
			if (otherElement.weight != weight) return false;
		}
		return true;
	}
	
	/*
	 * VALUE PROVIDERS
	 */
	
	public static ElementValueProvider<PersonMock, String> name() {
		return new ElementValueProvider<PersonMock, String>() {
			@Override
			public String getValue(PersonMock element) {
				return element.getName();
			}
		};
	}
	
	public static ElementValueProvider<PersonMock, Integer> age() {
		return new ElementValueProvider<PersonMock, Integer>() {
			@Override
			public Integer getValue(PersonMock element) {
				return element.getAge();
			}
		};
	}
	
	public static ElementValueProvider<PersonMock, Double> weight() {
		return new ElementValueProvider<PersonMock, Double>() {
			@Override
			public Double getValue(PersonMock element) {
				return element.getWeight();
			}
		};
	}
	
	/*
	 * UPDATERS
	 */
	
	public static Updater<PersonMock> nameTo(final String newName) {
		return new Updater<PersonMock>() {
			@Override
			public void update(PersonMock element) {
				element.setName(newName);
			}
		};
	}
	
	public static Updater<PersonMock> ageTo(final int newAge) {
		return new Updater<PersonMock>() {
			@Override
			public void update(PersonMock element) {
				element.setAge(newAge);
			}
		};
	}
	
	public static Updater<PersonMock> weightTo(final double newWeight) {
		return new Updater<PersonMock>() {
			@Override
			public void update(PersonMock element) {
				element.setWeight(newWeight);
			}
		};
	}

}
