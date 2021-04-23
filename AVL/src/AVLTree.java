public class AVLTree<T extends Comparable<T>> {
    private AVLTreeNode<T> root;

    public AVLTree() {
        root = null;
    }

    public static <T extends Comparable<T>> AVLTreeNode<T> getSuccessor(AVLTreeNode<T> item) {
        if(item == null)
            throw new IllegalArgumentException("Argument can't be null");
        if(item.getRight() != null)
            return getMinRec(item.getRight());

        AVLTreeNode<T> cParent = item.getParent();
        AVLTreeNode<T> current = item;
        while (cParent != null && cParent.getLeft() == current) {
            current = cParent;
            cParent = cParent.getParent();
        }
        return cParent;
    }
    public AVLTreeNode<T> getPredecessor(AVLTreeNode<T> item) {
        if(item == null)
            throw new IllegalArgumentException("Argument can't be null");
        if(item.getLeft() != null)
            return getMaxRec(item.getLeft());

        AVLTreeNode<T> cParent = item.getParent();
        AVLTreeNode<T> current = item;
        while (cParent != null && cParent.getRight() == current) {
            current = cParent;
            cParent = cParent.getParent();
        }
        return cParent;
    }

    public T getMin() {
        if (root == null)
            throw new RuntimeException("Min value isn't exist in empty tree");
        return getMinRec(root).getKey();
    }
    public T getMax() {
        if (root == null)
            throw new RuntimeException("Max value isn't exist in empty tree");
        return getMaxRec(root).getKey();
    }

    public AVLTreeNode<T> search(T key) {
        if (root == null)
            return null;
        return searchRec(root, key);
    }
    public void insert(T key) {
        if (root == null)
            root = new AVLTreeNode<>(key, null);
        else {
            Boolean heightIncrease = false;
            insertRec(root, key, heightIncrease);
        }
    }
    public void delete(T key) {

    }


    private static <T extends Comparable<T>> AVLTreeNode<T> getMaxRec(AVLTreeNode<T> root) {
        if (root.getRight() != null)
            return getMaxRec(root.getRight());
        return root;
    }
    private static <T extends Comparable<T>> AVLTreeNode<T> getMinRec(AVLTreeNode<T> root) {
        if (root.getLeft() != null)
            return getMinRec(root.getLeft());
        return root;
    }
    private static <T extends Comparable<T>> AVLTreeNode<T> searchRec(AVLTreeNode<T> root, T key) {
        if (root == null)
            return null;
        if (root.getKey().equals(key))
            return root;
        if (root.getKey().compareTo(key) < 0)
            return searchRec(root.getRight(), key);
        return searchRec(root.getLeft(), key);
    }

    private static <T extends Comparable<T>> AVLTreeNode<T> smallRotateLeft(AVLTreeNode<T> root) {
        AVLTreeNode<T> right = root.getRight();
        root.setRight(right.getLeft());
        if(root.getParent() != null && root.getParent().getRight() == root) {
            root.getParent().setRight(right);
        }
        else if (root.getParent() != null) {
            root.getParent().setLeft(right);
        }
        right.setLeft(root);

        return right;
    }
    private static <T extends Comparable<T>> AVLTreeNode<T> smallRotateRight(AVLTreeNode<T> root) {
        AVLTreeNode<T> left = root.getLeft();
        root.setLeft(left.getRight());
        if(root.getParent() != null && root.getParent().getRight() == root) {
            root.getParent().setRight(left);
        }
        else if (root.getParent() != null) {
            root.getParent().setLeft(left);
        }
        left.setRight(root);

        return left;
    }
    private static <T extends Comparable<T>> AVLTreeNode<T> bigRotateLeft(AVLTreeNode<T> root) {
        smallRotateRight(root.getRight());
        return smallRotateLeft(root);
    }
    private static <T extends Comparable<T>> AVLTreeNode<T> bigRotateRight(AVLTreeNode<T> root) {
        smallRotateLeft(root.getLeft());
        return smallRotateRight(root);
    }

    private static <T extends Comparable<T>> void insertRec(AVLTreeNode<T> root, T key, Boolean heightIncrease) {
        if (root.getKey().compareTo(key) < 0) {
            if (root.getRight() != null) {
                insertRec(root.getRight(), key, heightIncrease);
                if (heightIncrease) {
                    if (root.getBalance() == -1) {
                        root.setBalance(0);
                        heightIncrease = false;
                    }
                    else if(root.getBalance() == 0) {
                        root.setBalance(1);
                    }
                    else {
                        if (root.getRight().getBalance() == 1) {
                            AVLTreeNode<T> right = smallRotateLeft(root);
                            root.setBalance(-1);
                            right.setBalance(-1);
                            heightIncrease = false;
                        }
                    }
                }
            }
            else {
                AVLTreeNode<T> node = new AVLTreeNode<>(key, root);
                if (root.getBalance() == 0)
                    heightIncrease = true;
                root.setBalance(root.getBalance() + 1);
            }
        }
        else {

        }
    }
}
