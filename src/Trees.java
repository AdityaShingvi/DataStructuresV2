class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

public class Trees {
    public static void main(String[] args) {
        TreeNode bt = createBinaryTree();
        TreeNode bst = createBinarySearchTree();

        System.out.println(height(bt));
        System.out.println(size(bst));

        inorder(bst);

        System.out.println(isSame(bst,bt));
        System.out.println(searchBST(bst, 40).data);

        inorder(insertInBST(bst, 80));

        System.out.println(isBalancedTree(bt));

        inorder(createBST(new int[]{2, 4, 6, 8, 10, 12}));
    }

    public static boolean isBalancedTree(TreeNode root) {
        if (root == null) return true;

        int lht = height(root.left);
        int rht = height(root.right);

        if (Math.abs(lht - rht) > 1)
            return false;
        else
            return isBalancedTree(root.left)
            && isBalancedTree(root.right);
    }

    public static TreeNode createBST(int[] arr) {
        return createTree(arr, 0, arr.length);
    }

    public static TreeNode createTree(int[] arr, int lo, int hi) {
        if (lo < hi) {
            int mid = (lo + hi) / 2;
            TreeNode node = new TreeNode(arr[mid], null, null);
            node.left = createTree(arr, lo, mid);
            node.right = createTree(arr, mid + 1, hi);
            return node;
        }
        return null;
    }

    public static TreeNode insertInBST(TreeNode root, int key) {
        TreeNode node = new TreeNode(key, null, null);

        if (root == null) return node;

        TreeNode current = root, parent = root;
        while (current != null) {
            parent = current;
            if (current.data > key)
                current = current.left;
            else
                current = current.right;
        }

        if (parent.data < key)
            parent.right = node;
        else
            parent.left = node;

        return root;
    }

    public static TreeNode searchBST(TreeNode root, int key) {
        if (root == null) return null;

        if (root.data == key) return root;

        else if (root.data > key)
            return searchBST(root.left, key);
        else
            return searchBST(root.right, key);
    }

    public static int height(TreeNode root) {
        if (root == null) return 0;

        int lht = height(root.left);
        int rht = height(root.right);

        return Math.max(lht, rht) + 1;
    }

    public static int size(TreeNode root) {
        if (root == null) return 0;

        int lsize = size(root.left);
        int rsize = size(root.right);

        return lsize + rsize + 1;
    }

    public static boolean isSame(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;

        return t1.data == t2.data
                && isSame(t1.left, t2.left)
                && isSame(t1.right, t2.right);
    }

    public static void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public static void preorder(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static void postorder(TreeNode root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }


    public static TreeNode createBinaryTree() {
        TreeNode n7 = new TreeNode(70, null, null);
        TreeNode n6 = new TreeNode(60, null, null);
        TreeNode n5 = new TreeNode(50, null, null);
        TreeNode n4 = new TreeNode(40, null, null);
        TreeNode n3 = new TreeNode(30, n6, n7);
        TreeNode n2 = new TreeNode(20, n4, n5);
        TreeNode n1 = new TreeNode(10, n2, n3);

        return n1;
    }

    public static TreeNode createBinarySearchTree() {
        TreeNode n7 = new TreeNode(10, null, null);
        TreeNode n6 = new TreeNode(70, null, null);
        TreeNode n5 = new TreeNode(40, null, null);
        TreeNode n4 = new TreeNode(20, n7, null);
        TreeNode n3 = new TreeNode(60, null, n6);
        TreeNode n2 = new TreeNode(30, n4, n5);
        TreeNode n1 = new TreeNode(50, n2, n3);

        return n1;
    }
}
