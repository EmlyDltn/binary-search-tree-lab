package edu.unl.raikes.BinarySearchTreeLab;

/**
 * Holds the functionality and the values needed for a Person object
 * 
 * @author Emily Dalton
 *
 */
public class Person implements Comparable<Person> {
	int key;
	String name;

	/**
	 * Constructs a new person object
	 * 
	 * @param NUID The NUID for a person
	 * @param name The name of a person
	 */
	Person(int NUID, String name) {
		this.key = NUID;
		this.name = name;
	}

	/**
	 * Prints a string representation of a Person
	 */
	public String toString() {
		return "NUID: " + this.key + "  Name: " + name;
	}

	/**
	 * Compares two people based on their NUID
	 */
	public int compareTo(Person other) {
		return Integer.compare(key, other.key);
	}
}
