package tree;

import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>>
    extends AbstractTree<T>
    implements BinaryTree<T> {

  // @Override AbstractTree class methods //--------------------------------------------------------------------------

  @Override
  String getName() {
    return "BinarySearchTree";
  }

  @Override
  BinaryNode<T> getRoot() {
    return (BinaryNode<T>) super.getRoot();
  }

  @Override
  BinaryNode<T> createNode(T object, Node<T> parent) {
    return new BinaryNode<>(object, (BinaryNode<T>) parent);
  }

  // @Override BinaryTree interface methods //--------------------------------------------------------------------------

  @Override
  public List<T> getInOrder() {
    return getRoot().getInOrder(new LinkedList<>());
  }

  @Override
  public void printInOrder() {
    getRoot().printInOrder();
  }

  @Override
  public void printLevelOrder() {
    System.out.println(this);
    List<BinaryNode<T>> nodesInLevel = new LinkedList<>();
    nodesInLevel.add(getRoot());
    int printedElements = 0;
    int levels = 0;
    while (!nodesInLevel.isEmpty()) {
      int oldPrintedElements = printedElements;
      List<BinaryNode<T>> nodesInNextLevel = new LinkedList<>();
      for (BinaryNode<T> node : nodesInLevel) {
        if (node != null) {
          System.out.print(node);
          nodesInNextLevel.add(node.getLeft());
          nodesInNextLevel.add(node.getRight());
          printedElements++;
        }
      }
      System.out.println();
      nodesInLevel = nodesInNextLevel;
      if (oldPrintedElements != printedElements) {
        levels++;
      }
    }
    System.out.printf("Levels: %d, Printed elements: %d\n\n", levels, printedElements);
  }

  @Override
  public boolean insertObject(T object) {
    BinaryNode<T> parent = null;
    BinaryNode<T> current = getRoot();

    while (current != null && !current.getObject().equals(object)) {
      parent = current;
      current = current.getObject().compareTo(object) > 0
          ? current.getLeft()
          : current.getRight();
    }

    if (current == null) {
      current = createNode(object, parent);

      if (parent == null) {
        setRoot(current);
      } else if (parent.getObject().compareTo(current.getObject()) > 0) {
        parent.setLeft(current);
      } else {
        parent.setRight(current);
      }

      size++;
      fixAfterInsertion(current);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean removeObject(T object) {
    BinaryNode<T> current = getRoot();

    while (current != null && !current.getObject().equals(object)) {
      current = current.getObject().compareTo(object) > 0
          ? current.getLeft()
          : current.getRight();
    }

    if (current != null) {
      if (current.hasBothChildren()) {
        BinaryNode<T> successor = findSuccessor(current);
        current.setObject(successor.getObject());
        current = successor;
      }

      BinaryNode<T> child = current.hasLeft() ? current.getLeft() : current.getRight();
      if (child != null) {
        child.setParent(current.getParent());
      }

      fixAfterDeletion(current);

      if (current.isLeft()) {
        current.getParent().setLeft(child);
      } else if (current.isRight()) {
        current.getParent().setRight(child);
      } else {
        setRoot(child);
      }

      size--;
      return true;
    } else {
      return false;
    }
  }

  // BinarySearchTree class methods //--------------------------------------------------------------------------------
  // node utilities

  BinaryNode<T> findMax(BinaryNode<T> node) {
    while (node.hasRight()) {
      node = node.getRight();
    }
    return node;
  }

  BinaryNode<T> findMin(BinaryNode<T> node) {
    while (node.hasLeft()) {
      node = node.getLeft();
    }
    return node;
  }

  BinaryNode<T> findSuccessor(BinaryNode<T> node) {
    return node == null ? null : findMin(node.getRight());
  }

  BinaryNode<T> findPredecessor(BinaryNode<T> node) {
    return node == null ? null : findMax(node.getLeft());
  }

  void rotateLeft(BinaryNode<T> node) {
    // unlink our right node
    BinaryNode<T> temp = node.getRight();

    if (temp == null) {
      throw new NullPointerException(
          String.format("Cannot rotate left, right child of %s doesn't exist", node));
    }

    // link left child of temp as our right node
    node.setRight(temp.getLeft());

    // link temp to our parent
    temp.setParent(node.getParent());
    if (node.isLeft()) {
      node.getParent().setLeft(temp);
    } else if (node.isRight()) {
      node.getParent().setRight(temp);
    }

    // link us as left node of temp
    temp.setLeft(node);
    node.setParent(temp);
  }

  void rotateRight(BinaryNode<T> node) {
    // unlink our left node
    BinaryNode<T> temp = node.getLeft();

    if (temp == null) {
      throw new NullPointerException(
          String.format("Cannot rotate right, left child of %s doesn't exist", node));
    }

    // link right child of temp as our left node
    node.setLeft(temp.getRight());

    // link temp to our parent
    temp.setParent(node.getParent());
    if (node.isLeft()) {
      node.getParent().setLeft(temp);
    } else if (node.isRight()) {
      node.getParent().setRight(temp);
    }

    // link us as right node of temp
    temp.setRight(node);
    node.setParent(temp);
  }

  // for subclasses

  void fixAfterInsertion(BinaryNode<T> inserted) {
  }

  void fixAfterDeletion(BinaryNode<T> replaced) {
  }

}
