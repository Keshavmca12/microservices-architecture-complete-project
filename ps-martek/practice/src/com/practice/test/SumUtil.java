package com.practice.test;

public class SumUtil {

	static int sum = 0;
	
	public static int sumOfNodes(TreeNode node) {
		if(node == null) {
			return 0;
		}
		sum += node.data;
		if(node.left != null) {
			sumOfNodes(node.left);
		}
		if(node.right != null) {
			sumOfNodes(node.right);
		}
		return sum;
	}
	
}

