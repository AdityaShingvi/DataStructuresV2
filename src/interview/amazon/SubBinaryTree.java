package interview.amazon;

public class SubBinaryTree {

	public static int isSubTree(TNode root1, TNode root2){
		if(root2 == null && root1== null){
			return 1;
		}
		if(root2 == null || root1 == null){
			return -1;
		}
		
		if(root1.value == root2.value){
			
			if(isSubTree(root1.left, root2.left) == 1 && isSubTree(root1.right, root2.right) == 1)
				return 1;
			else
				return -1;
		}
		else{
			if(isSubTree(root1.left, root2) == 1 || isSubTree(root1.right, root2) == 1)
				return 1;
			else
				return -1;
		}
			
	}
}
