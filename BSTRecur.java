package airbnb;

/**
 * Created by EricLee on 10/31/16.
 */
public class BSTRecur {
    public static void main(String[] args) {
        BST tree = new BST();
        tree.insert(1);
        System.out.println(tree.find(1));
        tree.insert(2);
        tree.delete(1);
        System.out.println(tree.find(2));
        System.out.println(tree.find(1));
        tree.insert(3);
        tree.insert(10);
        tree.insert(212);
        tree.insert(22);
        tree.delete(10);
        tree.printAll();
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    static class BST {
        private TreeNode root;
        public BST() {

        }

        public boolean find(int val) {
            TreeNode cur = root;
            while (cur != null) {
                if (cur.val == val) return true;
                if (cur.val < val) cur = cur.right;
                else cur = cur.left;
            }
            return false;
        }

        public void insert(int val) {
            root = insert(root, val);
        }

        // Internal helper function for inserting
        private TreeNode insert(TreeNode node, int val) {
            if (node == null) return new TreeNode(val);
            if (node.val == val) return node;
            if (node.val > val) node.left = insert(node.left, val);
            else node.right = insert(node.right, val);
            return node;
        }

        public void delete(int val) {
            root = delete(root, val);
        }

        private TreeNode delete(TreeNode node, int val) {
            if (node == null) return null;
            if (node.val < val) node.right = delete(node.right, val);
            else if (node.val > val) node.left = delete(node.left, val);
            else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;
                // When the deleting node has two children
                TreeNode temp = node;
                node = findInorderSuccessor(temp.right);
                node.right = deleteMin(temp.right);
                node.left = temp.left;
            }
            return node;
        }

        public void printAll() {
            printAll(root);
        }

        private void printAll(TreeNode node) {
            if (node == null) return;
            printAll(node.left);
            System.out.print(node.val + " ");
            printAll(node.right);
        }

        private TreeNode findInorderSuccessor(TreeNode node) {
            if (node.left == null) return node;
            return findInorderSuccessor(node.left);
        }

        private TreeNode deleteMin(TreeNode node) {
            if (node.left == null) return node.right;
            node.left = deleteMin(node.left);
            return node;
        }
    }
}
