import java.lang.Comparable;

public class AVLTreeNode<T extends Comparable> {
    private AVLTreeNode<T> parent, left, right;
    private T key;
    private int balance;

    public AVLTreeNode(T key, AVLTreeNode<T> parent) {
        this.key = key;
        this.parent = parent;
        this.left = this.right = null;
        this.balance = 0;
    }

    public AVLTreeNode<T> getParent() {
        return parent;
    }
    public AVLTreeNode<T> getLeft() {
        return left;
    }
    public AVLTreeNode<T> getRight() {
        return right;
    }

    public void setLeft(AVLTreeNode<T> nLeft) {
        this.left = nLeft;
        if (nLeft != null)
            nLeft.parent = this;
    }
    public void setRight(AVLTreeNode<T> nRight) {
        this.right = nRight;
        if (nRight != null)
            nRight.parent = this;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public T getKey() {
        return key;
    }

    public int getBalance() {
        return balance;
    }
}
