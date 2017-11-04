package tree;

import java.util.List;

class BinaryNode<T> extends Node<T> {

  private BinaryNode<T> left;
  private BinaryNode<T> right;

  BinaryNode(T object, BinaryNode<T> parent) {
    super(object, parent);
  }

  // @Override Node class methods //----------------------------------------------------------------------------------

  @Override
  BinaryNode<T> getParent() {
    return (BinaryNode<T>) super.getParent();
  }

  // BinaryNode class methods //--------------------------------------------------------------------------------------
  // getters

  BinaryNode<T> getLeft() {
    return left;
  }

  BinaryNode<T> getRight() {
    return right;
  }

  boolean hasLeft() {
    return left != null;
  }

  boolean hasRight() {
    return right != null;
  }

  boolean hasBothChildren() {
    return hasLeft() && hasRight();
  }

  boolean isLeft() {
    return getParent() != null && this == getParent().getLeft();
  }

  boolean isRight() {
    return getParent() != null && this == getParent().getRight();
  }

  // setters

  void setLeft(BinaryNode<T> node) {
    this.left = node;
    if (left != null) {
      left.setParent(this);
    }
  }

  void setRight(BinaryNode<T> node) {
    this.right = node;
    if (right != null) {
      right.setParent(this);
    }
  }

  // utilities

  void printInOrder() {
    if (left != null) {
      left.printInOrder();
    }
    System.out.print(getObject() + " ");
    if (right != null) {
      right.printInOrder();
    }
  }

  List<T> getInOrder(List<T> list) {
    if (left != null) {
      left.getInOrder(list);
    }
    list.add(this.getObject());
    if (right != null) {
      right.getInOrder(list);
    }
    return list;
  }

}
