package edu.unl.raikes.BinarySearchTreeLab;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the values and functionality needed for a BinarySearchNode Object
 * 
 * @author Emily Dalton
 *
 */
class BinarySearchNode {
	protected BinarySearchNode parent;
	protected BinarySearchNode leftChild;
	protected BinarySearchNode rightChild;
	protected Person person;

	/**
	 * Initialize a new BinarySearchNode object The only required value is a person
	 * object
	 * 
	 * @param person
	 */
	BinarySearchNode(Person person) {
		this.person = person;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	boolean insert(Person data) {
		// Checks if the person already exits in the tree
		if (data == this.person) {
			// if the person already exists, tell the program to stop trying to find the
			// right location
			return false;
		}
		// Finds which child of the current node the new value fits into
		// If the compared keys return a negative, the new value will follow the left
		// child
		else if (Integer.compare(data.key, person.key) < 0) {
			// If the child is null, the value has found where it belongs and will be added
			// to the program
			if (leftChild == null) {
				setLeftChild(new BinarySearchNode(data));
				return true;
			} // If the child already exists, the program continues to recurse through the
				// tree, following the leftChild's branch.
			else {
				return leftChild.insert(data);
			}
		}
		// If the compared keys returns a positive, the new value will follow the right
		// child
		else if (Integer.compare(data.key, person.key) > 0) {
			// If the child is null, the value has found where it belongs and will be added
			// to the program
			if (rightChild == null) {
				setRightChild(new BinarySearchNode(data));
				return true;
			} // If the child already exists, the program continues to recurse through the
				// tree, following the leftChild's branch.
			else {
				return rightChild.insert(data);
			}
		}
		return false;
	}

	/**
	 * Checks if the children of a node are at the location specified by the key If
	 * they are, it will return in the value at that location, if not, it will
	 * continue to recurse through the tree
	 * 
	 * @param key
	 * @return
	 */
	BinarySearchNode search(int key) {
		// If the compared keys returns a negative, it means the key is on the
		// leftChild's path
		if (leftChild != null && Integer.compare(key, person.key) < 0) {
			return leftChild.search(key);
		}
		// If the compared keys returns a positive, then the value of the key of the
		// right child,
		// the key is located on the rightChild's path
		else if (rightChild != null && Integer.compare(key, person.key) > 0) {
			return rightChild.search(key);
		}
		// If the two keys are equal, it has found the right location and returns the
		// data held at that node
		else if (this.person.key == key) {
			return this;
		}
		// Otherwise, if the program has run through and not found a key equal to the
		// specified key, the key is not on the tree
		// and null is returned
		else {
			return null;
		}
	}

	/**
	 * Deletes the element of a specified key from the tree
	 * 
	 * @param key Location of the node to be deleted
	 * @return The node that has been deleted
	 */
	Person delete(int key) {

		BinarySearchNode node = search(key);
		if (node == null)
			return null;
		Person deleted = node.person;

		// If the node to be deleted has no children, the node is deleted from the tree
		if (node.leftChild == null && node.rightChild == null) {
			if (node.parent.leftChild == node)
				node.parent.setLeftChild(null);
			else if (node.parent.rightChild == node)
				node.parent.setRightChild(null);
		}
		// If the node to be deleted has two children, the minimum value from the right
		// child is found and replaces the deleted node
		//
		else if (node.leftChild != null && node.rightChild != null) {
			BinarySearchNode min = node.rightChild.getNodeWithMinValue();
			node.person = min.person;
			int minKey = min.person.key;
			min.delete(minKey);
		}
		// If the node to be deleted has only a left child, the parent is set to point
		// to the child instead
		else if (node.parent.leftChild == node) {
			BinarySearchNode newLeftChild = (node.leftChild != null) ? node.leftChild : node.rightChild;
			node.parent.setLeftChild(newLeftChild);
		}
		// If the node to be deleted has only a right child, the parent is set to point
		// to the child instead
		else if (node.parent.rightChild == node) {
			BinarySearchNode newRightChild = (node.leftChild != null) ? node.leftChild : node.rightChild;
			node.parent.setRightChild(newRightChild);
		}

		return deleted;
	}

	/**
	 * Searches for the value in a tree with the smallest value
	 * 
	 * @return The smallest value in a tree
	 */
	BinarySearchNode getNodeWithMinValue() {
		if (leftChild == null)
			return this;
		else
			return leftChild.getNodeWithMinValue();
	}

	/**
	 * Sets the value of a left child and tells the child which parent it belongs to
	 * 
	 * @param child The value of the new child
	 */
	void setLeftChild(BinarySearchNode child) {
		this.leftChild = child;
		if (child != null)
			child.parent = this;
	}

	/**
	 * Sets the value of a right child and tells the child which parent it belongs
	 * to
	 * 
	 * @param child The value of the new child
	 */
	void setRightChild(BinarySearchNode child) {
		this.rightChild = child;
		if (child != null)
			child.parent = this;
	}

	/**
	 * Returns a string representation of all the nodes in the tree Uses an in-order
	 * traversal to get all of the nodes in order
	 */
	public String toString() {
		String toReturn = "";
		// Performs an in-order traversal on the tree starting with the current node
		List<BinarySearchNode> nodes = new ArrayList<BinarySearchNode>();
		nodes = inOrderTraversal();
		// Adds all the nodes to the string
		for (BinarySearchNode person : nodes) {
			toReturn += "  " + person.person.toString() + "\n";
		}
		return toReturn;
	}

	/**
	 * Helper method for the inOrderTraversal
	 * 
	 * @return An arrayList with all the nodes in the tree
	 */
	public ArrayList<BinarySearchNode> inOrderTraversal() {
		ArrayList<BinarySearchNode> newList = new ArrayList<BinarySearchNode>();
		if (this != null) {
			newList = inOrderTraversal(this, newList);
		}
		return newList;
	}

	/**
	 * Performs an in order traversal through the tree
	 * 
	 * @param current The current node the method is checking for children
	 * @param list    The list of all the nodes that have been retrieved so far
	 * @return The final list with all of the nodes in order
	 */
	public ArrayList<BinarySearchNode> inOrderTraversal(BinarySearchNode current, ArrayList<BinarySearchNode> list) {
		if (current.leftChild != null) {
			inOrderTraversal(current.leftChild, list);
		}
		list.add(current);
		if (current.rightChild != null) {
			inOrderTraversal(current.rightChild, list);

		}
		return list;
	}

}