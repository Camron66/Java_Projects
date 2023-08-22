package app;
/**************************************************************
 Purpose/Description: Binary Search Tree Practice
 Author’s Panther ID:  6187231
 Certification:
 I hereby certify that this work is my own and none of it is the work of
 any other person.
 **************************************************************/
public class Problem1{
    public static void main(String[] args) {

        BinarySearchTree bst = new BinarySearchTree();//create an object of the BinarySearchTree class
//insert the elements in the bst

        bst.insert(4);

        bst.insert(2);

        bst.insert(5);

        bst.insert(1);

        bst.insert(3);

        System.out.println("a) positiveKeySum()\n" + bst.positiveKeySum());

        System.out.println("\nb) deleteMax()");

        bst.deleteMax(bst.root);
        bst.printTree();

        System.out.println("\nc) printTree()");

        bst.insert(5);
        bst.printTree();

        System.out.println("\nd) printPostorder");

        bst.postOrder();

        System.out.println("\nfind()-> find the element 10 in the tree: " + bst.find(10));

        System.out.println("\ninsert()-> insert the element 10 in the tree. " );

        bst.insert(10);

        System.out.println("\nfind()-> find the element 10 in the tree: " + bst.find(10));

    }
}
