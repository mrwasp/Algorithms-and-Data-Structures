package tree;

import java.util.List;

public interface BinaryTree<T> extends Tree<T> {

    List<T> getInOrder();

    void printInOrder();

    void printLevelOrder();


}
