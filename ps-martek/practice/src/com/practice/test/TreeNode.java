package com.practice.test;

import java.util.Objects;

public class TreeNode {

	int data;
	TreeNode left;
	TreeNode right;
	public TreeNode(int data) {
		this.data = data;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(data, left, right);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeNode other = (TreeNode) obj;
		return data == other.data && Objects.equals(left, other.left) && Objects.equals(right, other.right);
	}
	
	
	
	
}
