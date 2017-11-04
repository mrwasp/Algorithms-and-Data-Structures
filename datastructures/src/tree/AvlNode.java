package tree;

class AvlNode<T> extends BinaryNode<T> {

  private int balanceFactor;

  AvlNode(T object, AvlNode<T> parent) {
    super(object, parent);
    this.balanceFactor = 0;
  }

  // @Override Node class methods //----------------------------------------------------------------------------------

  @Override
  AvlNode<T> getParent() {
    return (AvlNode<T>) super.getParent();
  }

  String nodeToString() {
    return "[" + getBalanceFactor() + "]" + super.nodeToString();
  }

  // @Override BinaryNode class methods //----------------------------------------------------------------------------

  @Override
  public AvlNode<T> getLeft() {
    return (AvlNode<T>) super.getLeft();
  }

  @Override
  public AvlNode<T> getRight() {
    return (AvlNode<T>) super.getRight();
  }

  // AvlNode class methods //-----------------------------------------------------------------------------------------

  int getBalanceFactor() {
    return balanceFactor;
  }

  boolean isBalanced() {
    return -2 < balanceFactor && balanceFactor < 2;
  }

  void increaseLeft() {
    balanceFactor++;
  }

  void increaseRight() {
    balanceFactor--;
  }

  void setBalanceFactor(int balanceFactor) {
    this.balanceFactor = balanceFactor;
  }

}
