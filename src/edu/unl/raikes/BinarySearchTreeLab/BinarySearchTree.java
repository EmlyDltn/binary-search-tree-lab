package edu.unl.raikes.BinarySearchTreeLab;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds the methods needed for a Binary Search Tree Object
 * 
 * @author Emily Dalton
 *
 */
public class BinarySearchTree {
	boolean verbose = true;
	private BinarySearchNode root = null;
	private int size = 0;

	/**
	 * Finds an empty spot in the tree where the data fits and adds the new data to
	 * the tree
	 * 
	 * @param data The Person being added to the tree
	 */
	public void insert(Person data) {
		boolean inserted = false;
		// Checks if the current Node is empty, if so, it will add the new Node at that
		// location
		if (root == null) {
			root = new BinarySearchNode(data);
			inserted = true;
		} // If the node is not empty, it will continue to recurse through the tree till
			// it finds a empty spot where it fits
		else {
			inserted = root.insert(data);
		} // If a node was added, increase the size of the tree by one
		if (inserted) {
			size++;
		}
	}

	/**
	 * Finds the data at a specified location
	 * 
	 * @param key Location of the data you want to find
	 * 
	 * @return Data returned at the specified location
	 */
	public Person search(int key) {
		// Checks if the root is empty, if so, the key is not on the tree and null is
		// returned
		if (root == null) {
			return null;
		}

		BinarySearchNode found = root.search(key);
		// If a value is found at the key location, it is returned, otherwise the method
		// returns null
		if (found != null) {
			return found.person;
		} else {
			return null;
		}

	}

	/**
	 * Removes a Node from the tree
	 * 
	 * @param key The location of the node to be removed
	 * 
	 * @return The value of the removed node
	 */
	public Person delete(int key) {
		Person deleted = null;

		// Checks if the root is null and the tree is empty
		if (root == null) {
			return null;
		} // If the root is not null, the key location is found and the node removed
		else {
			// Covers case if the node to be removed is the root
			if (root.person.key == key) {
				// add fake root in case the element to be removed is the root.
				// (simplifies pointer logic)
				BinarySearchNode auxRoot = new BinarySearchNode(null);
				auxRoot.setLeftChild(root);
				// Deletes the root and sets the new parent for any existing children
				deleted = root.delete(key);
				// retrieve the root from the fake root (in case it changed)
				root = auxRoot.leftChild;
				// After the new root is set the old root is removed so the new root now has no
				// parent
				if (root != null)
					root.parent = null;
			} // Otherwise, the node is simply removed from the tree
			else {
				deleted = root.delete(key);
			} // If a node is deleted, the size is decreased by one
			if (deleted != null)
				size--;
			return deleted;
		}
	}

	/**
	 * Returns a string representation of a tree
	 */
	public String toString() {
		String toReturn = "Binary Search Tree of Size: " + size + "\n";
		// Returns a sorted string representation of all the persons in the tree
		if (root != null) {
			toReturn += root.toString();
		}
		return toReturn;
	}

}
