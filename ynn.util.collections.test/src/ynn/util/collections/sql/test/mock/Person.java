package ynn.util.collections.sql.test.mock;

import ynn.util.collections.sql.ElementValueProvider;
import ynn.util.collections.sql.Updater;

public class Person {
	
	private String name;
	private int age;
	private double weight;
	
	public Person() { }
	
	public Person(String name, int age, double weight) { 
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
			Person otherElement = (Person) obj;
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
	
	public static ElementValueProvider<Person, String> name() {
		return new ElementValueProvider<Person, String>() {
			@Override
			public String getValue(Person element) {
				return element.getName();
			}
		};
	}
	
	public static ElementValueProvider<Person, Integer> age() {
		return new ElementValueProvider<Person, Integer>() {
			@Override
			public Integer getValue(Person element) {
				return element.getAge();
			}
		};
	}
	
	public static ElementValueProvider<Person, Double> weight() {
		return new ElementValueProvider<Person, Double>() {
			@Override
			public Double getValue(Person element) {
				return element.getWeight();
			}
		};
	}
	
	/*
	 * UPDATERS
	 */
	
	public static Updater<Person> nameTo(final String newName) {
		return new Updater<Person>() {
			@Override
			public void update(Person element) {
				element.setName(newName);
			}
		};
	}
	
	public static Updater<Person> ageTo(final int newAge) {
		return new Updater<Person>() {
			@Override
			public void update(Person element) {
				element.setAge(newAge);
			}
		};
	}
	
	public static Updater<Person> weightTo(final double newWeight) {
		return new Updater<Person>() {
			@Override
			public void update(Person element) {
				element.setWeight(newWeight);
			}
		};
	}

}
