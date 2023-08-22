package app;

public class BinarySearchTreeNode {
    //declare the instance variables
    public int key;
    public BinarySearchTreeNode left;
    public BinarySearchTreeNode right;
    public BinarySearchTreeNode(int key){//define the constructor to initialize the variables
        this.left = null;
        this.right = null;
        this.key = key;
    }
    //define the getter and setter methods of the instance variables
    public int getKey()
    {
        return key;
    }
    public void setKey(int key)
    {
        this.key = key;
    }
    public BinarySearchTreeNode getLeft()
    {
        return left;
    }
    public void setLeft(BinarySearchTreeNode left)
    {
        this.left = left;
    }
    public BinarySearchTreeNode getRight()
    {
        return right;
    }

    public void setRight(BinarySearchTreeNode right)
    {
        this.right = right;
    }
}
