package app;

import java.util.ArrayList;
import java.util.List;

//define the class BinarySearchTree
 public class BinarySearchTree {
    public BinarySearchTreeNode root;
        public void insert(int key) {
            root = insertHelper(root, key);
        }

        private BinarySearchTreeNode insertHelper(BinarySearchTreeNode node, int key) {
            int count = 0;
            if (node == null)//if the node is null return the key
                return new BinarySearchTreeNode(key);
            if (key > node.key)//if key count is greater than node's key count
                count = +1;//increment the count
            else
            if (key < node.key)//if key count is less than node's key count
                count = -1;//assign count as -1
            else
                count = 0;//else assign count as 0

            if (count < 0)//if the count is less than 0
                node.left = insertHelper(node.left, key);
            else if (count > 0) //if the count is grater than 0
                node.right = insertHelper(node.right, key);
            else
                node.key = key;
            return node;
        }

        // definition of the delete
        public void delete(int key) {
            root = deleteHelper(root, key);
        }

        private BinarySearchTreeNode deleteHelper(BinarySearchTreeNode node, int key) {
            int value = 0;
            if (node == null)
                return null;
            if (key == 0)
                return node;
            if (key > node.key)//if key value is greater than node's key value
                value = +1;//increment the value
            else
            if (key < node.key)//if key value is less than node's key value
                value = -1;//assign value as -1
            else
                value = 0;//else assign value as 0
            if (value < 0)
                node.left = deleteHelper(node.left, key);
            else if (value > 0)
                node.right = deleteHelper(node.right, key);
            else {
                if (node.left == null)
                    return node.right;
                if (node.right == null)
                    return node.left;
                BinarySearchTreeNode temp = node;
//call the method min()
                node = min(node.right);
//call the method deleteMin()
                node.right = deleteMax(temp.right);
                node.left = temp.left;
            }
            return node;
        }

        public boolean find(int key) {
            return findHelper(root, key);
        }

        public boolean findHelper(BinarySearchTreeNode node, int key) {

//iterates over the tree anf find

//the key and returns the boolean value
            while (node != null) {
                if (key == node.key)
                    return true;
                else if (key < node.key)
                    node = node.left;
                else
                    node = node.right;
            }
            return false;
        }

        private BinarySearchTreeNode min(BinarySearchTreeNode n) {
            if (n.left == null)
                return n;
            return min(n.left);
        }
        //(a)definition of the method keySum()
//this method returns the sum of all the keys in the tree
//it calls the helper method keySumHelper(BinarySearchTreeNode,List)
        public int positiveKeySum() {
            List<Integer> keyList = new ArrayList<>() ;//create an ArrayList of Integer type
            int sum = 0;
            keySumHelper(root, keyList);//call the helper method keySumHelper(BinarySearchTreeNode,List)
            for (int i : keyList) {//Iterate over the list and find the sum of the key values in the list
                sum += i;
            }
            return sum;//returns the sum
        }

        //definition of the helper method keySumHelper()
//takes the BST node and list of key values
        public void keySumHelper(BinarySearchTreeNode node, List<Integer> keyList) {
//if the node is not null
            if (node != null) {
                if(node.key >= 0) {
                    keySumHelper(node.left, keyList);//call the method keySumHelper() recursively with left of the tree
                    keySumHelper(node.right, keyList);//call the method keySumHelper() recursively with right of the tree
                    keyList.add(node.key);//add the key value of the node to the keyList
                }
            }
        }
        //(b)definition of the method deleteMin()
//it delete the minimum element in the tree
        public BinarySearchTreeNode deleteMax(BinarySearchTreeNode node) {
            if (node.right == null)
                return node.left;//if the left of the node is null the return the right of the node
            else
                node.right = deleteMax(node.right);//else call the method deleteMin()recursively with the the left of the node
            return node;
        }
        //(c)definition of the method printTree()
//it calls the helper method printTreeHelper()
//to print the elements of the tree
        public void printTree() {
            printTreeHelper(root);
            System.out.println("");
        }
        //definition of the helper method printTreeHelper()
//this method prints the elements in the tree in increasing order
        private void printTreeHelper(BinarySearchTreeNode node) {
            if (node.right != null) {//if the left of the node is not null
                printTreeHelper(node.right);//call the method printTreeHelper() recursively with left of the node
            }
            System.out.print(node.key + " ");//print key value
            if (node.left != null) {//if the right of the node is not null
                printTreeHelper(node.left);//call the method printTreeHelper() recursively with right of the node
            }
        }

//(d)definition of the method postOrder()
//it calls the helper method postOrderHelper()
//to print the postorder of the tree

        public void postOrder() {
            postOrderHelper(root);
            System.out.println("");
        }
        //definition of the helper method postOrderHelper()
//this method prints the elements in the postorder of the tree
        private void postOrderHelper(BinarySearchTreeNode node) {
            if (node.left != null) {//if the left of the node is not null
                postOrderHelper(node.left);//call the method postOrderHelper() recursively with left of the node
            }
            if (node.right != null) {//if the right of the node is not null
                postOrderHelper(node.right);//call the method postOrderHelper() recursively with left of the node
            }
            System.out.print(node.key + " ");//print key value of the node
        }
        public static void main(String[] args) {

            BinarySearchTree bst = new BinarySearchTree();//create an object of the BinarySearchTree class
//insert the elements in the bst

            bst.insert(4);

            bst.insert(2);

            bst.insert(5);

            bst.insert(1);

            bst.insert(3);

            System.out.println("printTree()-> The elements of the tree in increasing order:");

            bst.printTree();

            System.out.println("\nprintPostOrder()-> The elements of the tree in PostOrder:");

            bst.postOrder();

            System.out.println("\nkeySum()-> The sum of the elements of the tree: " + bst.positiveKeySum());

            System.out.println("\ndeleteMin()-> deletes the minimum element of the tree.");

            bst.deleteMax(bst.root);

            System.out.println("\nprintTree()-> The elements of the tree in increasing order:");

            bst.printTree();

            System.out.println("\nfind()-> find the element 10 in the tree: " + bst.find(10));

            System.out.println("\ninsert()-> insert the element 10 in the tree. ");

            bst.insert(10);

            System.out.println("\nfind()-> find the element 10 in the tree: " + bst.find(10));

        }
    }