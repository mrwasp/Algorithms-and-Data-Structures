package tree;

public class AvlTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    // @Override AbstractTree class methods //--------------------------------------------------------------------------

    @Override
    String getName() {
        return "AvlTree";
    }

    @Override
    AvlNode<T> getRoot() {
        return (AvlNode<T>) super.getRoot();
    }

    @Override
    AvlNode<T> createNode(T object, Node<T> parent) {
        return new AvlNode<>(object, (AvlNode<T>) parent);
    }

    @Override
    // @Override BinarySearchTree class methods //----------------------------------------------------------------------
    void fixAfterInsertion(BinaryNode<T> insertedNode) {
        AvlNode<T> inserted = (AvlNode<T>) insertedNode;

        if (inserted.isLeft()) {
            inserted.getParent().increaseLeft();
        } else if (inserted.isRight()) {
            inserted.getParent().increaseRight();
        }

        while (inserted.getParent() != null) {
            inserted = inserted.getParent();
            int bf = inserted.getBalanceFactor();
            if (bf == -2) {
                if (inserted.getRight().getBalanceFactor() == 1) {
                    rightLeft(inserted);
                } else {
                    rightRight(inserted);
                }
                if (isRoot(inserted)) {
                    setRoot(inserted.getParent());
                }
                break;
            } else if (bf == 2) {
                if (inserted.getLeft().getBalanceFactor() == -1) {
                    leftRight(inserted);
                } else {
                    leftLeft(inserted);
                }
                if (isRoot(inserted)) {
                    setRoot(inserted.getParent());
                }
                break;
            } else if (bf == -1 || bf == 1) {
                if (inserted.isRight()) {
                    inserted.getParent().increaseRight();
                } else if (inserted.isLeft()) {
                    inserted.getParent().increaseLeft();
                }
            } else {
                break;
            }
        }
    }

    @Override
    void fixAfterDeletion(BinaryNode<T> replaced) {
        AvlNode<T> current = (AvlNode<T>) replaced;
        AvlNode<T> parent = current.getParent();

        while (parent != null) {
            if (parent.getBalanceFactor() == 0) {
                if (current.isLeft()) {
                    parent.increaseRight();
                } else if (current.isRight()) {
                    parent.increaseLeft();
                }
                break;
            } else if (parent.getBalanceFactor() == 1) {
                if (current.isLeft()) {
                    parent.setBalanceFactor(0);
                    current = parent;
                    parent = parent.getParent();
                } else {
                    if (getBalanceFactor(parent.getLeft()) == 0) {
                        leftLeft(parent);
                        if (isRoot(parent)) {
                            setRoot(parent.getParent());
                        }
                        break;
                    } else if (getBalanceFactor(parent.getLeft()) == 1) {
                        leftLeft(parent);
                        if (isRoot(parent)) {
                            setRoot(parent.getParent());
                        }
                        current = parent.getParent();
                        parent = current.getParent();
                    } else {
                        leftRight(parent);
                        if (isRoot(parent)) {
                            setRoot(parent.getParent());
                        }
                        current = parent.getParent();
                        parent = current.getParent();
                    }
                }
            } else if (parent.getBalanceFactor() == -1) {
                if (current.isRight()) {
                    parent.setBalanceFactor(0);
                    current = parent;
                    parent = parent.getParent();
                } else {
                    if (getBalanceFactor(parent.getRight()) == 0) {
                        rightRight(parent);
                        if (isRoot(parent)) {
                            setRoot(parent.getParent());
                        }
                        break;
                    } else if (getBalanceFactor(parent.getRight()) == -1) {
                        rightRight(parent);
                        if (isRoot(parent)) {
                            setRoot(parent.getParent());
                        }
                        current = parent.getParent();
                        parent = current.getParent();
                    } else {
                        rightLeft(parent);
                        if (isRoot(parent)) {
                            setRoot(parent.getParent());
                        }
                        current = parent.getParent();
                        parent = current.getParent();
                    }
                }
            }
        }
    }

    // AvlTree class methods //-----------------------------------------------------------------------------------------

    int getBalanceFactor(AvlNode<T> node) {
        return node == null ? 0 : node.getBalanceFactor();
    }


    void rightRight(AvlNode<T> node) {
        AvlNode<T> right = node.getRight();
        rotateLeft(node);
        if (right.getBalanceFactor() == -1) {
            node.setBalanceFactor(0);
            right.setBalanceFactor(0);
        } else {
            node.setBalanceFactor(-1);
            right.setBalanceFactor(1);
        }
    }
    void leftLeft(AvlNode<T> node) {
        AvlNode<T> left = node.getLeft();
        rotateRight(node);
        if (left.getBalanceFactor() == 1) {
            node.setBalanceFactor(0);
            left.setBalanceFactor(0);
        } else {
            node.setBalanceFactor(1);
            left.setBalanceFactor(-1);
        }
    }

    void rightLeft(AvlNode<T> node) {
        AvlNode<T> right = node.getRight();
        AvlNode<T> rightLeft = right.getLeft();
        rotateRight(right);
        rotateLeft(node);
        node.setBalanceFactor(rightLeft.getBalanceFactor() == -1 ? 1 : 0);
        right.setBalanceFactor(rightLeft.getBalanceFactor() == 1 ? -1 : 0);
        rightLeft.setBalanceFactor(0);
    }

    void leftRight(AvlNode<T> node) {
        AvlNode<T> left = node.getLeft();
        AvlNode<T> leftRight = left.getRight();
        rotateLeft(left);
        rotateRight(node);
        node.setBalanceFactor(leftRight.getBalanceFactor() == 1 ? -1 : 0);
        left.setBalanceFactor(leftRight.getBalanceFactor() == -1 ? 1 : 0);
        leftRight.setBalanceFactor(0);
    }
}
