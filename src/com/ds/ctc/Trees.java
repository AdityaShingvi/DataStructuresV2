package com.ds.ctc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Trees {
	
	public static int count = 0;
	
	public static void main(String args[]) 
    {
		Trees tree = new Trees();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(8);
        root.left.left.left.left = new TreeNode(18);
        root.left.left.left.right = new TreeNode(82);
  
        if(tree.isBinaryTreeBalanced(root))
            System.out.println("Tree is balanced");
        else
            System.out.println("Tree is not balanced");
        System.out.println(Trees.count);
        
        int arr[] = {1,2,3,4,5,6,7,8};
        tree.createMinimalBST(arr, 0, 7);
        
        tree.createLevelLinkLists(root);
    }
	// Difference between EACH of the left and right subtrees in the tree should not be > 1
	public boolean isBinaryTreeBalanced(TreeNode root) {
		if (root == null) return true;
		
		if (getHeight(root) == -1) return false;
		return true;
	}
	
	public int getHeight(TreeNode root) {
		Trees.count++;
		// exit condition
		if (root == null) return 0;
		
		int left = getHeight(root.left);
		int right = getHeight(root.right);
		
		if (left == -1 || right == -1) return -1;
		
		if(Math.abs(left - right) > 1) return -1;
		
		return Math.max(left, right) + 1;
	}

	// The center of the array will be the root. Left sub array will be left subtree, right sub array will be right subtree
	// Do this recursively
	public TreeNode createMinimalBST(int arr[], int start, int end){
		// exit condition
		if(end < start){
			return null;
		}
		int mid = (start + end) / 2;
		TreeNode root = new TreeNode(arr[mid]);
 		root.left = createMinimalBST(arr, start, mid - 1);
		root.right = createMinimalBST(arr, mid + 1, end);
		return root;
	}
	
	// Kind of DFS. i.e. Just keep track of the level while traversing using DFS
	// Note: Tree Level starts from 0 i.e root and linked list size starts from 1 when not empty
	public void createLevelLinkLists(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level) {
		if(root == null) return;
		
		LinkedList<TreeNode> list = null;
		if(lists.size() == level) {
			list = new LinkedList<TreeNode>();
			lists.add(list);
		} else {
			list = lists.get(level);
		}
		list.add(root);
		createLevelLinkLists(root.left, lists, level+1);
		createLevelLinkLists(root.right, lists, level+1);
	}
	
	public ArrayList<LinkedList<TreeNode>> createLevelLinkLists(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
		createLevelLinkLists(root, lists, 0);
		return lists;
	}
	
	public boolean checkIfBST(TreeNode root, int min, int max) {
		if (root == null) return false;
		
		if (root.data <= min || root.data > max) return false;
		
		if (!checkIfBST(root.left, min, root.data)) return false;
		if (!checkIfBST(root.right, root.data, max)) return false;
		
		return true;
	}

	// Algo: Rule 1 - Inorder successor of any node in BST HAS TO BE on right subtree of that node 
	// Rule 2 - If right subtree is empty then it is the root of this entire subtree which has this entire LEFT subtree as a LEFT child
	// Rule 3 - If rule 1 and rule2 are false then it is the end of tree and there is no successor
	//          50
	//		  /    \
	//		 20     70
	//      /  \    / 
	//	  18   22  60
	// For ex: Inorder successor(20) = 22 ......as per Rule 1
	// Inorder successor(22) = 50 .... as per Rule 2
	// Inorder successor(18) = 20 .... as per Rule 2
	// Inorder successor(60) = null .... as per Rule 3

	public TreeNode inorderSuccessorBST(TreeNode root, TreeNode node) {
		if (node == null) return null;
		
		if (node.right != null) {
			 return findSmallestBSTNode(node.right);
		} else {
			TreeNode successor = null;
			while (root != null) {
				if (node.data < root.data) {
					successor = root;
					root = root.left;
				} else if (node.data > root.data) {
					root = root.right;
				} else {
					break;
				}
			}
			return successor;
		}
	}

	// BST rule: leftmost node in the subtree is the smallest node and rightmost is the largest
	public TreeNode findSmallestBSTNode(TreeNode node) {
		while(node.left != null)
			node = node.left;
		return node;
	}

	// Refer: https://www.youtube.com/watch?v=zHP9vhpUKEQ
	// Algo1 Implemented below: Find path from root to N1 in a stack
	// Find path from root to N2 in another stack
	// Compare the 2 stacks upto the node where stack values are same and save that node
	// If the stack values differ at any node, the node before that node is the commonAncestor
	// 
	// Algo2: Findout the inorder and postorder traversal of the tree
	// From the inorder traversal, get the list of all nodes in between the 2 given nodes n1, n2
	// Iterate over this list to see which of these nodes appears last in the postorder list. That node will be the 
	// Refer https://www.youtube.com/watch?v=LFjCr2yDJdc
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
		// if (root == null) return null; // not needed here as findPath() has this check
		if (node1.data == node2.data) return node1; //or node2
		
		Stack<TreeNode> pathToN1 = findPath(root, node1);
		Stack<TreeNode> pathToN2 = findPath(root, node2);
		
		if (pathToN1 == null || pathToN2 == null) return null;
		
		TreeNode commonAncestor = null;
		while(!pathToN1.isEmpty() && !pathToN2.isEmpty()) {
			TreeNode n1 = pathToN1.pop();
			TreeNode n2 = pathToN2.pop();
			
			if(n1.data == n2.data) commonAncestor = n1;
			else break;
		}
		return commonAncestor;
	}

	public Stack<TreeNode> findPath(TreeNode root, TreeNode node) {
		if (root == null) return null;
		
		if(root.data == node.data) {
			Stack<TreeNode> stack = new Stack<TreeNode>();
			stack.push(root);
			return stack;
		}
		
		Stack<TreeNode> left = findPath(root.left, node);
		Stack<TreeNode> right = findPath(root.right, node);
		
		if (left != null) {
			left.push(root);
			return left;
		}
		if (right != null) {
			right.push(root);
			return right;
		}
		return null;
	}

}

class TreeNode {
	public int data;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode(int data) {
		this.data = data;
		left = right = null;
	}
}
