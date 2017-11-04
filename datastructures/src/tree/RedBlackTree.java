package tree;

public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {

  // @Override AbstractTree class methods //--------------------------------------------------------------------------

  @Override
  ColoredNode<T> getRoot() {
    return (ColoredNode<T>) super.getRoot();
  }

  ColoredNode<T> createNode(T object, Node<T> parent) {
    return new ColoredNode<>(object, (ColoredNode<T>) parent);
  }

  @Override
  String getName() {
    return "RedBlackTree";
  }

  // @Override BinarySearchTree class methods //----------------------------------------------------------------------

  @Override
  void fixAfterInsertion(BinaryNode<T> insertedNode) {
    ColoredNode<T> inserted = (ColoredNode<T>) insertedNode;

    while (inserted != null && !isRoot(inserted) && inserted.getParent().isRed()) {
      if (inserted.getParent().isLeft()) {
        ColoredNode<T> uncle = inserted.getParent().getParent().getRight();
        if (isRed(uncle)) {
          uncle.paintBlack();
          inserted.getParent().paintBlack();
          inserted = inserted.getParent().getParent();
          inserted.paintRed();
        } else {
          if (inserted.isRight()) {
            inserted = inserted.getParent();
            rotateLeft(inserted);
          }
          inserted.getParent().paintBlack();
          inserted = inserted.getParent().getParent();
          inserted.paintRed();
          rotateRight(inserted);
          if (isRoot(inserted)) {
            setRoot(inserted.getParent());
          }
        }
      } else {
        ColoredNode<T> uncle = inserted.getParent().getParent().getLeft();
        if (isRed(uncle)) {
          uncle.paintBlack();
          inserted.getParent().paintBlack();
          inserted = inserted.getParent().getParent();
          inserted.paintRed();
        } else {
          if (inserted.isLeft()) {
            inserted = inserted.getParent();
            rotateRight(inserted);
          }
          inserted.getParent().paintBlack();
          inserted = inserted.getParent().getParent();
          inserted.paintRed();
          rotateLeft(inserted);
          if (isRoot(inserted)) {
            setRoot(inserted.getParent());
          }
        }

      }
    }
    getRoot().paintBlack();
  }

  @Override
  void fixAfterDeletion(BinaryNode<T> replacedNode) {

    ColoredNode<T> replaced = (ColoredNode<T>) replacedNode;

    replaced = replaced == null ? null
        : replaced.hasLeft() ? replaced.getLeft()
            : replaced.hasRight() ? replaced.getRight()
                : replaced;

    if (replaced == null) {
      return;
    }

    while (!isRoot(replaced) && replaced.isBlack()) {
      if (replaced.isLeft()
          || replaced.getObject().compareTo(replaced.getParent().getObject()) < 0) {
        ColoredNode<T> sibling = replaced.getParent().getRight();
        if (isRed(sibling)) {
          sibling.paintBlack();
          replaced.getParent().paintRed();
          rotateLeft(replaced.getParent());
          sibling = replaced.getParent().getRight();
        }
        if (isBlack(sibling.getLeft()) && isBlack(sibling.getRight())) {
          sibling.paintRed();
          replaced = replaced.getParent();
        } else {
          if (isBlack(sibling.getRight())) {
            paintBlack(sibling.getLeft());
            sibling.paintRed();
            rotateRight(sibling);
            sibling = replaced.getParent().getRight();
          }
          sibling.setColor(replaced.getParent().getColor());
          replaced.getParent().paintBlack();
          if (sibling.getRight() != null) {
            sibling.getRight().paintBlack();
          }
          rotateLeft(replaced.getParent());
          replaced = getRoot();
        }
      } else {
        ColoredNode<T> sibling = replaced.getParent().getLeft();
        if (isRed(sibling)) {
          sibling.paintBlack();
          replaced.getParent().paintRed();
          rotateRight(replaced.getParent());
          sibling = replaced.getParent().getLeft();
        }
        if (isBlack(sibling.getLeft()) && isBlack(sibling.getRight())) {
          sibling.paintRed();
          replaced = replaced.getParent();
        } else {
          if (isBlack(sibling.getLeft())) {
            paintBlack(sibling.getRight());
            sibling.paintRed();
            rotateLeft(sibling);
            sibling = replaced.getParent().getLeft();
          }
          sibling.setColor(replaced.getParent().getColor());
          replaced.getParent().paintBlack();
          sibling.getLeft().paintBlack();
          rotateRight(replaced.getParent());
          replaced = getRoot();
        }
      }
    }
    replaced.paintBlack();
  }

  // RedBlackTree class methods //------------------------------------------------------------------------------------

  boolean isBlack(ColoredNode<T> node) {
    return node == null || node.isBlack();
  }

  boolean isRed(ColoredNode<T> node) {
    return node != null && node.isRed();
  }

  private void paintBlack(ColoredNode<T> node) {
    if (node != null) {
      node.paintBlack();
    }
  }

}
