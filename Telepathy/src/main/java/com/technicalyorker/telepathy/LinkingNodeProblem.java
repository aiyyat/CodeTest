package com.technicalyorker.telepathy;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author achuth
 *
 */

public class LinkingNodeProblem {
	private Map<Integer, Node> list = new TreeMap<>();

	public void perform(Node root) {
		recurse(root, 0);
	}

	public void recurse(Node root, Integer level) {
		Node[] children = root.getChildren();
		if (null == list.get(level)) {
			list.put(level, root);
		} else {
			list.get(level).setRight(root);
			list.put(level, root);
		}
		if (null != children) {
			for (Node child : children) {
				recurse(child, level + 1);
			}
		}
	}
}

class Node {
	public Node(String name) {
		this.name = name;
	}

	public Node(String name, Node[] children) {
		this.name = name;
		this.children = children;
	}

	private String name;
	private Node[] children;
	private Node right;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node[] getChildren() {
		return children;
	}

	public void setChildren(Node[] children) {
		this.children = children;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Node [name=" + name + ", children=" + Arrays.toString(children)
				+ ", right=" + right + "]";
	}
}
